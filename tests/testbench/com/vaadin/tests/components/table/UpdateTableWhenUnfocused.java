package com.vaadin.tests.components.table;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.tests.components.AbstractTestCase;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class UpdateTableWhenUnfocused extends AbstractTestCase {
    @Override
    public void init() {
        Window mainWindow = new Window("Tabletest Application");
        VerticalLayout root = new VerticalLayout();

        // Create a tab sheet with two tabs. One sheet contains a table, the
        // other one can be empty.
        final Table tbl = createTable();
        tbl.setSizeFull();
        VerticalLayout sheet1Layout = new VerticalLayout();
        sheet1Layout.setSizeFull();
        sheet1Layout.addComponent(tbl);
        VerticalLayout sheet2Layout = new VerticalLayout();
        sheet2Layout.setSizeFull();
        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(sheet1Layout, "tab1");
        tabSheet.addTab(sheet2Layout, "tab2");
        tabSheet.setHeight("5000px");
        tabSheet.setWidth("100%");
        root.addComponent(tabSheet);

        // Add a button for refreshing the table.
        final Button b1 = new Button("Refresh table");
        root.addComponent(b1);
        b1.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                // Set focus to the button. This is required for the automatic
                // test to work as expected.
                b1.focus();
                tbl.refreshRowCache();
            }
        });

        mainWindow.addComponent(root);
        setMainWindow(mainWindow);

    }

    private Table createTable() {
        Table tbl = new Table("Table");
        Container ds = new IndexedContainer();
        tbl.setImmediate(true);
        tbl.setMultiSelect(true);
        tbl.setSelectable(true);
        ds.addContainerProperty("id", Integer.class, null);
        ds.addContainerProperty("col1", String.class, null);
        ds.addContainerProperty("col2", String.class, null);
        ds.addContainerProperty("col3", String.class, null);
        for (int i = 0; i < 1000; i++) {
            Item item = ds.addItem(i);
            item.getItemProperty("id").setValue(i);
            item.getItemProperty("col1").setValue("col1_" + i);
            item.getItemProperty("col2").setValue("col2_" + i);
            item.getItemProperty("col3").setValue("col3_" + i);
        }
        tbl.setContainerDataSource(ds);
        return tbl;
    }

    @Override
    protected String getDescription() {
        return "Clicking the button after selecting a row in the table should not cause the window to scroll.";
    }

    @Override
    protected Integer getTicketNumber() {
        return 12749;
    }

}
