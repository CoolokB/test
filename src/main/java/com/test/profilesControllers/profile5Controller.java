package com.test.profilesControllers;

import com.test.*;
import com.test.tools.Inter;
import com.test.tools.MyPrinter;
import com.test.tools.ProfileInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

public class profile5Controller implements Inter {

    @FXML private Pane profile5pane;
    @FXML private Label profile5name;
    @FXML private Label profile5description;
    @FXML private Button profile5printer;

    @Autowired
    MyPrinter printer;

    public void initialize(Person person) {
        profile5name.setText(person.getName() + " " + person.getLastName());
        profile5description.setText("Профільна група № 5" + " " + ProfileInfo.profile5Info);
        profile5printer.setOnAction(event -> printer.print(profile5printer, profile5pane));
    }

}


