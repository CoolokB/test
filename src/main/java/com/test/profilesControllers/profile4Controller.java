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

public class profile4Controller implements Inter {

    @FXML private Pane profile4pane;
    @FXML private Label profile4name;
    @FXML private Label profile4description;
    @FXML private Button profile4printer;

    @Autowired
    MyPrinter printer;

    public void initialize(Person person) {
        profile4name.setText(person.getName() + " " + person.getLastName());
        profile4description.setText("Профільна група № 4"+ " " + ProfileInfo.profile4Info);
        profile4printer.setOnAction(event -> printer.print(profile4printer, profile4pane));
    }
}


