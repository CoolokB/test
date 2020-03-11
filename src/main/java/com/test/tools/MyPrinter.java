package com.test.tools;

import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MyPrinter {

    public void print(Button b, Pane p) {

        Stage stage = (Stage) b.getScene().getWindow();
        PrinterJob job = PrinterJob.createPrinterJob();
        job.showPageSetupDialog(stage);
        boolean proceed = job.showPrintDialog(stage);
        boolean printed = job.printPage(p);
        if (proceed) {
            if (printed) {
                job.endJob();
            }
        }
    }

    public void print(Button b, Pane p, Pane p2) {

        Stage stage = (Stage) b.getScene().getWindow();
        PrinterJob job = PrinterJob.createPrinterJob();
        job.showPageSetupDialog(stage);
        boolean proceed = job.showPrintDialog(stage);
        boolean printed = job.printPage(p);
        boolean printed1 = job.printPage(p2);
        if (proceed) {

            if (printed) {
                job.endJob();
            }
            if (printed1) {
                job.endJob();
            }
        }
    }

}
