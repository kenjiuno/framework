package com.vaadin.tests.components.table;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Table;

public class TableTabIndex extends TestBase {

	@Override
	protected void setup() {
		Table t1 = createTable(1);
		Table t0 = createTable(0);
		Table tm1 = createTable(-1);
		Table t2 = createTable(2);

		addComponent(t1);
		addComponent(t0);
		addComponent(tm1);
		addComponent(t2);
	}

	private Table createTable(int tabIndex) {
		Table t = new Table();
		t.setSelectable(true);
		t.addContainerProperty("Foo", String.class, "Foo");
		t.addContainerProperty("Bar", String.class, "Bar");
		t.setTabIndex(tabIndex);
		t.setCaption("TabIndex: " + tabIndex);
		t.addItem(1);
		t.addItem(2);
		t.addItem(3);
		t.setPageLength(5);
		return t;
	}

	@Override
	protected String getDescription() {
		return "Tab order should be 1-2-0 and -1 should never be reached";
	}

	@Override
	protected Integer getTicketNumber() {
		return 13144;
	}

}
