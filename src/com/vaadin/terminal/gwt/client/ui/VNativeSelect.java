/*
@VaadinApache2LicenseForJavaFiles@
 */

package com.vaadin.terminal.gwt.client.ui;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.vaadin.terminal.gwt.client.BrowserInfo;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.Util;

public class VNativeSelect extends VOptionGroupBase implements Field {

    public static final String CLASSNAME = "v-select";

    protected TooltipListBox select;

    private boolean firstValueIsTemporaryNullItem = false;

    public VNativeSelect() {
        super(new TooltipListBox(false), CLASSNAME);
        select = (TooltipListBox) optionsContainer;
        select.setSelect(this);
        select.setVisibleItemCount(1);
        select.addChangeHandler(this);
        select.setStyleName(CLASSNAME + "-select");

    }

    @Override
    protected void buildOptions(UIDL uidl) {
        select.setClient(client);
        select.setEnabled(!isDisabled() && !isReadonly());
        select.clear();
        firstValueIsTemporaryNullItem = false;

        if (isNullSelectionAllowed() && !isNullSelectionItemAvailable()) {
            // can't unselect last item in singleselect mode
            select.addItem("", (String) null);
        }
        boolean selected = false;
        for (final Iterator<?> i = uidl.getChildIterator(); i.hasNext();) {
            final UIDL optionUidl = (UIDL) i.next();
            select.addItem(optionUidl.getStringAttribute("caption"),
                    optionUidl.getStringAttribute("key"));
            if (optionUidl.hasAttribute("selected")) {
                select.setItemSelected(select.getItemCount() - 1, true);
                selected = true;
            }
        }
        if (!selected && !isNullSelectionAllowed()) {
            // null-select not allowed, but value not selected yet; add null and
            // remove when something is selected
            select.insertItem("", (String) null, 0);
            select.setItemSelected(0, true);
            firstValueIsTemporaryNullItem = true;
        }
        if (BrowserInfo.get().isIE6()) {
            // lazy size change - IE6 uses naive dropdown that does not have a
            // proper size yet
            Util.notifyParentOfSizeChange(this, true);
        }
    }

    @Override
    protected String[] getSelectedItems() {
        final ArrayList<String> selectedItemKeys = new ArrayList<String>();
        for (int i = 0; i < select.getItemCount(); i++) {
            if (select.isItemSelected(i)) {
                selectedItemKeys.add(select.getValue(i));
            }
        }
        return selectedItemKeys.toArray(new String[selectedItemKeys.size()]);
    }

    @Override
    public void onChange(ChangeEvent event) {

        if (select.isMultipleSelect()) {
            client.updateVariable(id, "selected", getSelectedItems(),
                    isImmediate());
        } else {
            client.updateVariable(id, "selected", new String[] { ""
                    + getSelectedItem() }, isImmediate());
        }
        if (firstValueIsTemporaryNullItem) {
            // remove temporary empty item
            select.removeItem(0);
            firstValueIsTemporaryNullItem = false;

            /*
             * Workaround to a chrome bug that may cause value change event not
             * to fire when selection is done with keyboard.
             * 
             * http://dev.vaadin.com/ticket/10109
             * 
             * Problem is confirmed to exist only on Chrome-Win, but just
             * execute in for all webkits. Probably exists also in other
             * webkits/blinks on windows.
             */
            if (BrowserInfo.get().isWebkit()) {
                select.getElement().blur();
                select.getElement().focus();
            }
        }
    }

    @Override
    public void setHeight(String height) {
        select.setHeight(height);
        super.setHeight(height);
    }

    @Override
    public void setWidth(String width) {
        select.setWidth(width);
        super.setWidth(width);
    }

    @Override
    protected void setTabIndex(int tabIndex) {
        ((TooltipListBox) optionsContainer).setTabIndex(tabIndex);
    }

    public void focus() {
        select.setFocus(true);
    }

}