package com.test.uiControllers;

import com.test.*;
import com.test.enums.TestType;
import com.test.tools.Inter;
import com.test.tools.Loader;
import com.test.tools.PagesContainer;
import com.test.tools.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageController implements Inter {

    public ToggleGroup group;

    @FXML private RadioButton answer1;
    @FXML private RadioButton answer2;
    @FXML private RadioButton answer3;
    @FXML private RadioButton answer4;
    @FXML private RadioButton answer5;
    @FXML private RadioButton answer6;
    @FXML private Button next;
    @FXML private Label title;
    @FXML private Label header;
    private static int index;
    private List<RadioButton> buttons;
    private List<Page> pageList;
    private TestType type;
    private Person person;
    private PagesContainer container;

    public void initialize(PagesContainer container,TestType type,Person person) {

        this.type = type;
        this.container = container;
        this.person = person;

        buttons = Arrays.asList(answer1, answer2, answer3, answer4, answer5, answer6);

        List<Page> motivationPages = container.getMPages();
        List<Page> caPages = container.getCaPages();
        List<Page> cpPages = container.getCpPages();

        switch (type) {
            case MOTIVATION:
                pageList = motivationPages;
                title.setText("Тест визначення мотивації та потенцілу");
                break;
            case CLARIFYING_A:
                pageList = caPages;
                title.setText("Уточнення рівня мотивації");
                break;
            case CLARIFYING_P:
                pageList = cpPages;
                title.setText("Уточнення рівня мотивації");
                break;
        }

        fillPage(pageList.get(index));

       boolean isSelected=false;


    }

    @FXML private void next(){

        buttons.forEach(b -> {
            if (b.isSelected()) pageList.get(index).setAnswer(b.getText());
        });

        if (index == pageList.size() - 1) {

            if (type == TestType.POTENTIAL) {

                List<Page> list = new ArrayList<>();

                list.addAll(container.getMPages());
                list.addAll(pageList);

                Loader.load("/cards/MotivationTestResults.fxml", container.getMPages(), person);
                Loader.load("/cards/PotentialTestResults.fxml", pageList, person);
                Loader.load("/cards/Summary.fxml", list, person);

                next.getScene().getWindow().hide();
                return;
            }

            if (type == TestType.MOTIVATION) {
                index = -1;
                type = TestType.POTENTIAL;
                container.setMPages(pageList);
                pageList = container.getPPages();

            }

            if (type == TestType.CLARIFYING_A) {

                Loader.load("/cards/ClarifyingTestResults.fxml", container.getCaPages(), person);
                next.getScene().getWindow().hide();
                return;
            }

            if (type == TestType.CLARIFYING_P) {

                Loader.load("/cards/ClarifyingTestResults.fxml", container.getCpPages(), person);
                next.getScene().getWindow().hide();
                return;
            }
        }

        index++;
        fillPage(pageList.get(index));

    }

    private void fillPage(Page page){

        buttons.forEach(b -> b.setVisible(false));
        buttons.forEach(b -> b.setSelected(false));
        header.setText(page.getQuestion());
        Tools.setButtons(page, buttons);

    }

}