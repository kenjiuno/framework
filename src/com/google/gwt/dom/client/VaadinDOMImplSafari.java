/*
@VaadinApache2LicenseForJavaFiles@
 */
package com.google.gwt.dom.client;

/**
 * Overridden to workaround GWT issue #6194. Remove this when updating to a
 * newer GWT that fixes the problem (2.3.0 possibly). Must be in this package as
 * the whole DOMImpl hierarchy is package private and I really did not want to
 * copy all the parent classes into this one...
 */
class VaadinDOMImplSafari extends DOMImplWebkit {
    @Override
    public int getAbsoluteLeft(Element elem) {
        // Chrome returns a float in certain cases (at least when zoom != 100%).
        // The |0 ensures it is converted to an int.
        return super.getAbsoluteLeft(elem) | 0;
    }

    @Override
    public int getAbsoluteTop(Element elem) {
        // Chrome returns a float in certain cases (at least when zoom != 100%).
        // The |0 ensures it is converted to an int.
        return super.getAbsoluteTop(elem) | 0;
    }

    @Override
    public int touchGetPageX(Touch touch) {
        return super.touchGetPageX(touch) | 0;
    }

    @Override
    public int touchGetPageY(Touch touch) {
        return super.touchGetPageY(touch) | 0;
    }

    @Override
    public int touchGetClientX(Touch touch) {
        return super.touchGetClientX(touch) | 0;
    }

    @Override
    public int touchGetClientY(Touch touch) {
        return super.touchGetClientY(touch) | 0;
    }

    @Override
    public int touchGetScreenX(Touch touch) {
        return super.touchGetScreenX(touch) | 0;
    }

    @Override
    public int touchGetScreenY(Touch touch) {
        return super.touchGetScreenY(touch) | 0;
    }
}
