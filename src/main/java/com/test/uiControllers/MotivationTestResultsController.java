package com.test.uiControllers;

import com.test.*;
import com.test.enums.Level;
import com.test.enums.TestType;
import com.test.tools.Inter;
import com.test.tools.MyPrinter;
import com.test.tools.ProfileInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

public class MotivationTestResultsController implements Inter {

    @FXML private Label surname;
    @FXML private Label firstAnswer;
    @FXML private Label secondAnswer;
    @FXML private Label thirdAnswer;
    @FXML private Label fourthAnswer;
    @FXML private Label fifthAnswer;
    @FXML private Label sixthAnswer;
    @FXML private Label seventhAnswer;
    @FXML private Label eighthAnswer;
    @FXML private Label ninthAnswer;
    @FXML private Label tenthAnswer;
    @FXML private Label testResult;
    @FXML private Button print;
    @FXML private Pane MotivationTestAnswers;

    @Autowired
    MyPrinter printer;

    public void initialize(List<Page> pages, Person person) {

        List<Label> labels = Arrays.asList(firstAnswer,secondAnswer,thirdAnswer,fourthAnswer,fifthAnswer,
                sixthAnswer,seventhAnswer, eighthAnswer,ninthAnswer,tenthAnswer);

        int points = 0;

        for (int i = 0; i < labels.size(); i++) {

            labels.get(i).setText(pages.get(i).getAnswer());
            points += pages.get(i).getPoints();
        }

        surname.setText(person.getName() + " " + person.getLastName());

        person.setResult111(points);

        String info = "";

        Level level = ProfileInfo.getLevel(points, TestType.MOTIVATION);

        switch (level) {

            case LOW:
                info = "Низький рівень мотивації до працевлаштування";
                break;
            case MEDIUM:
                info = "Середній рівень мотивації до працевлаштування";
                break;
            case HIGH:
                info = "Високий рівень мотивації до працевлаштування";
                break;
        }

        testResult.setText(info + " ( кількість балів - " + points + " )");

        print.setOnAction(event ->
                printer.print(print, MotivationTestAnswers)
        );
    }
}




