/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.components.table;

import java.util.Set;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class SelectAllRows extends TestBase {

	static final int TOTAL_NUMBER_OF_ROWS = 300;

	@Override
	protected Integer getTicketNumber() {
		return 13244;
	}

	@Override
	protected void setup() {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		addComponent(layout);

		final Table table = new Table();
		table.setImmediate(true);
		table.setMultiSelect(true);
		table.setSelectable(true);
		table.addContainerProperty("row", String.class, null);
		layout.addComponent(table);

		Button button = new Button("Count");
		layout.addComponent(button);

		final Label label = new Label();
		label.setCaption("Selected count:");
		layout.addComponent(label);

		button.addListener(new Button.ClickListener() {

			public void buttonClick(Button.ClickEvent event) {
				Set selected = (Set) table.getValue();
				label.setValue(String.valueOf(selected.size()));
			}
		});

		for (int i = 0; i < TOTAL_NUMBER_OF_ROWS; i++) {
			Object itemId = table.addItem();
			table.getContainerProperty(itemId, "row").setValue("row " + i);
		}
	}

	@Override
	protected String getDescription() {
		return "Selecting all rows does not work by selecting first row, press shift then select last row";
	}

}
