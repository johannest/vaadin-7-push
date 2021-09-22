package org.test;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class PushView extends VerticalLayout {

    Chart chart = new Chart(ChartType.AREASPLINE);
    DataSeries series = new DataSeries();
    List<TestDTO> dtos = new ArrayList<>();

    public PushView() {
        setSizeFull();

        Configuration configuration = chart.getConfiguration();
        configuration.addSeries(series);
        double y = Math.random();
        series.add(
                new DataSeriesItem(0, y),
                true, false);

        Button button2 = new Button("Start filling the memory", e -> {
            new FeederThread(UI.getCurrent()).start();
        });
        addComponents(chart, button2);
    }

    class FeederThread extends Thread {
        int count = 1;
        private final UI ui;

        public FeederThread(UI ui) {
            this.ui = ui;
        }

        @Override
        public void run() {
            try {
                // Update the data for a while
                while (count < 50) {
                    Thread.sleep(500);

                    // Fill some memory
                    for (int i = 0; i < 50000; i++) {
                        dtos.add(new TestDTO());
                    }

                    ui.access(() -> {
                        double y = Math.random();
                        series.add(
                                new DataSeriesItem(count++, y),
                                true, count > 10);
                    });
                }

                // Inform that we have stopped running
                ui.access(() -> setContent(new Label("Done!")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setContent(Label label) {
        System.out.println("done");
        addComponent(label);
    }
}