package org.test;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Widgetset("org.test.MyWidgetset")
@SpringUI
@Push
public class MyUI extends UI {
    PushView pushView = null;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        Button button2 = new Button("Show Push View",
                (Button.ClickListener) event -> {
                    layout.addComponent(pushView = new PushView());
                });

        Button button3 = new Button("Hide Push View",
                (Button.ClickListener) event -> {
                    layout.removeComponent(pushView);
                    pushView = null;
                });

        layout.addComponents(button2, button3);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }
}
