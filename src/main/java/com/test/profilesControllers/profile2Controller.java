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

public class profile2Controller implements Inter {

    @FXML private Pane profile2pane;
    @FXML private Label profile2name;
    @FXML private Button profile2printer;
    @FXML private Label profile2description;

    @Autowired
    MyPrinter printer;

    public void initialize(Person person) {
        profile2name.setText(person.getName() + " " + person.getLastName());
        profile2description.setText("Профільна група № 2" + " " + ProfileInfo.profile2Info);
        profile2printer.setOnAction(event -> printer.print(profile2printer, profile2pane));
    }
}


