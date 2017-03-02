package com.vaadin.tests.components.textfield;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TextFieldFocusIE8 extends TestBase {

    @Override
    protected void setup() {
		VerticalLayout vl = new VerticalLayout();

		final Label label = new Label("Result");
		label.setVisible(false);

		final TextField tf = new TextField("Search");
		tf.setTextChangeEventMode(TextChangeEventMode.LAZY);
		tf.setImmediate(true);
		tf.addListener(new TextChangeListener() {

			public void textChange(TextChangeEvent event) {

				if (event.getText().length() > 2 ) {
					label.setVisible(true);
				} else {
					label.setVisible(false);
				}
			}
		});

		vl.addComponent(tf);
		vl.addComponent(label);
		addComponent(vl);
    }

	@Override
	protected String getDescription() {
		return "TextField loses focus when a component is displayed (TextChangeEvent)";
	}

	@Override
	protected Integer getTicketNumber() {
		return 9157;
	}

}
