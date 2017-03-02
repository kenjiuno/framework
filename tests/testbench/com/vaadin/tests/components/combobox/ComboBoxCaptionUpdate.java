package com.vaadin.tests.components.combobox;

import com.vaadin.tests.components.ComponentTestCase;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

public class ComboBoxCaptionUpdate extends ComponentTestCase<ComboBox> {

    @Override
    protected Class<ComboBox> getTestClass() {
        return ComboBox.class;
    }

    @Override
    protected void initializeComponents() {
        VerticalLayout layout = new VerticalLayout();

        final ComboBox c = new ComboBox();
        c.setNullSelectionAllowed(true);
        c.addItem(new Integer(0));
        c.addItem(new Integer(1));
        c.setItemCaption(new Integer(0), "Original caption");
        c.setItemCaption(new Integer(1), "Other element 1");
        c.select(new Integer(0));
        layout.addComponent(c);

        Button b = new Button("Change caption", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                c.setItemCaption(new Integer(0), "New caption");
            }
        });
        layout.addComponent(b);

        addComponent(layout);

    }

    @Override
    protected String getDescription() {
        return "Changing the caption of the selected item should update the combobox textbox";
    }

    @Override
    protected Integer getTicketNumber() {
        return 8954;
    }


}
