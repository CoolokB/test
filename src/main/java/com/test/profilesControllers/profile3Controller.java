package com.test.profilesControllers;

import com.test.tools.Inter;
import com.test.tools.MyPrinter;
import com.test.Person;
import com.test.tools.ProfileInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

public class profile3Controller implements Inter {

    @FXML private Pane profile3pane;
    @FXML private Label profile3name;
    @FXML private Label profile3description;
    @FXML private Button profile3printer;

    @Autowired MyPrinter printer;

    public void initialize(Person person) {
        profile3name.setText(person.getName() + " " + person.getLastName());
        profile3description.setText("Профільна група № 3"+ " " + ProfileInfo.profile3Info);
        profile3printer.setOnAction(event -> printer.print(profile3printer, profile3pane));
    }
}


