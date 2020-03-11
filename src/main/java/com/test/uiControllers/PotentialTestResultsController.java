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

public class PotentialTestResultsController implements Inter {

    @FXML private Label surname;
    @FXML private Label firstAnswer;
    @FXML private Label secondAnswer;
    @FXML private Label thirdAnswer;
    @FXML private Label fourthAnswer;
    @FXML private Label fifthAnswer;
    @FXML private Label sixthAnswer;
    @FXML private Label seventhAnswer;
    @FXML private Label eightAnswer;
    @FXML private Label ninthAnswer;
    @FXML private Label tenthAnswer;
    @FXML private Label testResult;
    @FXML private Label eleventhAnswer;
    @FXML private Pane PotentialAnswers;
    @FXML private Pane potentialAnswersAdditional;
    @FXML private Button print;

    @Autowired
    MyPrinter printer;

    public void initialize(List<Page> list, Person person) {
        surname.setText(person.getName()+" "+person.getLastName());

        List<Label> labels = Arrays.asList(firstAnswer,secondAnswer,thirdAnswer,fourthAnswer,fifthAnswer,
                sixthAnswer,seventhAnswer, eightAnswer,ninthAnswer,tenthAnswer,eleventhAnswer);

        int points = 0;

        for (int i = 0; i < labels.size(); i++) {

            labels.get(i).setText(list.get(i).getAnswer());
            points += list.get(i).getPoints();
        }

        String info="";

        Level level = ProfileInfo.getLevel(points, TestType.POTENTIAL);

        person.setResult113(points);

        switch (level) {

            case LOW:
                info="Низький потенціал до працевлаштування";
                break;
            case MEDIUM:
                info="Середній потенціал до працевлаштування";
                break;
            case HIGH:
                info="Високий потенціал до працевлаштування";
                break;
        }

        testResult.setText(info+" ( кількість балів - "+points+" )");

        print.setOnAction(event -> {
            printer.print(print, PotentialAnswers, potentialAnswersAdditional);
        });
    }
}
