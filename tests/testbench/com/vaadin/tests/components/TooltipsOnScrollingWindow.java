package com.vaadin.tests.components;

import com.vaadin.tests.util.TestUtils;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class TooltipsOnScrollingWindow extends TestBase {

    @Override
    protected void setup() {

        TestUtils
                .injectCSS(
                        getMainWindow(),
                        ".v-generated-body { overflow: auto; } "
                                + ".v-app, .v-view { overflow: visible !important;}"
                                + ".hoverable-label { position: fixed; bottom: 10px; right: 10px;  }"
                                + ".hidden-label { position: absolute; top: 2000px; left: 2000px;}"
                                /*
                                 * IE6 fixes: it doesn't support fixed position
                                 * and breaks if height/width of body and view
                                 * are not set to auto
                                 */
                                + ".v-ie6.v-generated-body { height: auto; width: auto;}"
                                + ".v-ie6 .v-app, .v-ie6 .v-view { height: auto; }"
                                + ".v-ie6 .hoverable-label { position: absolute; top: 1987px; left: 1975px; }");

        getLayout().getParent().setHeight("4000px");
        getLayout().getParent().setWidth("4000px");
        getLayout().setHeight("4000px");
        getLayout().setWidth("4000px");

        CssLayout layout = new CssLayout();
        layout.setHeight("4000px");
        layout.setWidth("4000px");
        addComponent(layout);

        Label hoverableLabel = new Label("Hover me");
        hoverableLabel.setDebugId("hoverable-label");
        hoverableLabel.setStyleName("hoverable-label");
        hoverableLabel.setWidth("-1px");
        hoverableLabel.setDescription("Tooltip");
        layout.addComponent(hoverableLabel);

        Label hiddenLabel = new Label("Hidden");
        hiddenLabel.setStyleName("hidden-label");
        hiddenLabel.setWidth("-1px");
        layout.addComponent(hiddenLabel);

        getMainWindow().scrollIntoView(hiddenLabel);
    }

    @Override
    protected String getDescription() {
        return "Tooltip is displayed in the wrong place when component is at lower edge of the screen and application with following the css is scrolled vertically.";
    }

    @Override
    protected Integer getTicketNumber() {
        return 9862;
    }

}
