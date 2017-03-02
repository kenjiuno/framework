package com.vaadin.tests.components.tree;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Tree;

public class TreeTabIndex extends TestBase {

    @Override
    protected void setup() {
        Tree t1 = createTree(1);
        Tree t0 = createTree(0);
        Tree tm1 = createTree(-1);
        Tree t2 = createTree(2);

        addComponent(t1);
        addComponent(t0);
        addComponent(tm1);
        addComponent(t2);
    }

    private Tree createTree(int tabIndex) {
        Tree t = new Tree();
        t.setSelectable(true);
        t.addContainerProperty("Foo", String.class, "Foo");
        t.addContainerProperty("Bar", String.class, "Bar");
        t.setTabIndex(tabIndex);
        t.setCaption("TabIndex: " + tabIndex);
        t.addItem(1);
        t.addItem(2);
        t.addItem(3);
        return t;
    }

    @Override
    protected String getDescription() {
        return "Tab order should be 1-2-0 and -1 should never be reached";
    }

    @Override
    protected Integer getTicketNumber() {
        return 13218;
    }

}
