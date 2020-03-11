package com.test.profilesControllers;

import com.test.tools.Inter;
import com.test.tools.MyPrinter;
import com.test.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

public class profile1Controller implements Inter {

        @FXML private Pane profile1pane;
        @FXML private Label profile1name;
        @FXML private Button profile1printer;

        @Autowired MyPrinter printer;

    public void initialize(Person person) {
        profile1name.setText(person.getName()+" "+person.getLastName());
        profile1printer.setOnAction(event -> printer.print(profile1printer,profile1pane));
    }
}


