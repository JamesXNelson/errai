package org.jboss.workspace.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.WindowCloseListener;
import org.gwt.mosaic.ui.client.WindowPanel;
import org.jboss.workspace.client.framework.AcceptsCallback;
import org.jboss.workspace.client.listeners.ClickCallbackListener;


public class WSModalDialog implements AcceptsCallback {
    Label message = new Label("Warning!");
    AcceptsCallback callbackTo;

    DockPanel dockPanel;

    Button okButton;
    ClickCallbackListener okListener;

    Button cancelButton;
    ClickCallbackListener cancelListener;

    WindowPanel window;

    public WSModalDialog() {
        window = new WindowPanel("Alert!");
        window.setWidth("400px");

        window.setAnimationEnabled(true);
        window.setResizable(false);

        dockPanel = new DockPanel();

        dockPanel.add(new Image("images/ui/icons/redflag.png"), DockPanel.WEST);
        dockPanel.add(message, DockPanel.CENTER);

        message.getElement().getStyle().setProperty("padding", "5px");

        HorizontalPanel buttonPanel = new HorizontalPanel();

        okButton = new Button("OK");
        okListener = new ClickCallbackListener(this, AcceptsCallback.MESSAGE_OK);
        okButton.addClickListener(okListener);

        buttonPanel.add(okButton);

        cancelButton = new Button("Cancel");
        cancelListener = new ClickCallbackListener(this, AcceptsCallback.MESSAGE_CANCEL);
        cancelButton.addClickListener(cancelListener);

        dockPanel.add(cancelButton, DockPanel.SOUTH);

        buttonPanel.add(cancelButton);

        dockPanel.add(buttonPanel, DockPanel.SOUTH);
        dockPanel.setCellHorizontalAlignment(buttonPanel, DockPanel.ALIGN_RIGHT);
        dockPanel.setCellHeight(buttonPanel, "45px");

        window.setWidget(dockPanel);
    }


    public void callback(String message) {
        callbackTo.callback(message);
        window.hide();
    }

    public void ask(String message, final AcceptsCallback callbackTo) {
        this.callbackTo = callbackTo;
        this.message.setText(message);
        window.addWindowCloseListener(new WindowCloseListener() {
            public String onWindowClosing() {
                return null;
            }

            public void onWindowClosed() {
                callbackTo.callback("WindowClosed");
            }
        });

    }

    public Button getOkButton() {
        return okButton;
    }

    public void setOkButton(Button okButton) {
        this.okButton = okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

    public void showModal() {
        window.showModal();
    }

}
