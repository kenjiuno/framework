/* 
@VaadinApache2LicenseForJavaFiles@
 */

package com.vaadin.terminal.gwt.client.ui;

import com.google.gwt.user.client.Command;
import com.vaadin.terminal.gwt.client.Util;

/**
 * 
 */
public abstract class Action implements Command {

    protected ActionOwner owner;

    protected String iconUrl = null;

    protected String caption = "";

    public Action(ActionOwner owner) {
        this.owner = owner;
    }

    /**
     * Executed when action fired
     */
    public abstract void execute();

    public String getHTML() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<div>");
        if (getIconUrl() != null) {
            sb.append("<img src=\"" + Util.escapeAttribute(getIconUrl())
                    + "\" alt=\"icon\" />");
        }
        sb.append(getCaption());
        sb.append("</div>");
        return sb.toString();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String url) {
        iconUrl = url;
    }
}