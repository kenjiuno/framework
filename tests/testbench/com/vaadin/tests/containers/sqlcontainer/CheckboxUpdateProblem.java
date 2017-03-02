package com.vaadin.tests.containers.sqlcontainer;

import java.sql.SQLException;

import com.vaadin.Application;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

public class CheckboxUpdateProblem extends Application implements
        Property.ValueChangeListener {
    private final DatabaseHelper databaseHelper = new DatabaseHelper();
    private Table testList;
    private final HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

    private TestForm testForm = new TestForm();

    @Override
    public void init() {
        setMainWindow(new Window("Test window"));
        horizontalSplit.setSizeFull();
        testList = new Table();

        horizontalSplit.setFirstComponent(testList);
        testList.setSizeFull();
        testList.setContainerDataSource(databaseHelper.getTestContainer());
        testList.setSelectable(true);
        testList.setImmediate(true);
        testList.addListener(this);

        databaseHelper.getTestContainer().addListener(
                new ItemSetChangeListener() {
                    public void containerItemSetChange(ItemSetChangeEvent event) {
                        Object selected = testList.getValue();
                        if (selected != null) {
                            testForm.setItemDataSource(testList
                                    .getItem(selected));
                        }
                    }
                });

        testForm = new TestForm();
        testForm.setItemDataSource(null);

        horizontalSplit.setSecondComponent(testForm);

        getMainWindow().setContent(horizontalSplit);
    }

    public void valueChange(ValueChangeEvent event) {

        Property property = event.getProperty();
        if (property == testList) {
            Item item = testList.getItem(testList.getValue());

            if (item != testForm.getItemDataSource()) {
                testForm.setItemDataSource(item);
            }
        }

    }

    private class TestForm extends Form implements Button.ClickListener {

        private final Button save;

        private TestForm() {
            setSizeFull();
            setWriteThrough(false);
            setInvalidCommitted(false);

            save = new Button("Save", (ClickListener) this);
            getFooter().addComponent(save);
            getFooter().setVisible(false);
        }

        public void buttonClick(ClickEvent event) {
            if (event.getSource() == save) {
                super.commit();

                try {
                    databaseHelper.getTestContainer().commit();
                    getMainWindow().showNotification("Saved");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void setItemDataSource(Item newDataSource) {
            super.setItemDataSource(newDataSource);

            if (newDataSource != null) {
                getFooter().setVisible(true);
            } else {
                getFooter().setVisible(false);
            }
        }

    }

}