package com.vaadin.tests.components.window;
import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class ModalFocus extends TestBase {


	@Override
	protected void setup() {


		getMainWindow().addComponent(new TextField());

		Window w = new Window();
		w.setWidth("500px");
		w.setModal(true);

		TextField tf = new TextField();
		tf.setWidth("100%");
		tf.setInputPrompt("Move focus to browser's address bar and try to focus this field with tabulator");
		w.addComponent(tf);
		TextField tf2 = new TextField();
		tf2.setWidth("100%");
		tf2.setInputPrompt("Second textfield");
		w.addComponent(tf2);
		getMainWindow().addWindow(w);
	}

	@Override
	protected String getDescription() {
		return "Testing that it's possible to move focus from browser's address field to modal window using TAB";
	}

	@Override
	protected Integer getTicketNumber() {
		return 5495;
	}

}