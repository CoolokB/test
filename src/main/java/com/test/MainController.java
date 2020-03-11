package com.test;

import com.test.enums.TestType;
import com.test.tools.Loader;
import com.test.tools.PagesContainer;
import com.test.tools.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @Autowired
    Person person;

    @Autowired
    PagesContainer pagesContainer;

    @FXML private TextField nameField;
    @FXML private TextField lastNameField;
    @FXML private RadioButton motivationAndPotentialButton;
    @FXML private RadioButton motivationClarifyingButton;
    @FXML private Button nextButton;
    @FXML private CheckBox fullScreenCheck;
    public static boolean fullScreenOn;

    @SuppressWarnings("unchecked")
    @PostConstruct
    void initialize() {

        initializeFields();

        File mQuestions = new File("files/mQuestions");
        File pQuestions = new File("files/pQuestions");
        File caQuestions = new File("files/caQuestions");
        File cpQuestions = new File("files/cpQuestions");

        File mAnswers = new File("files/mAnswers");
        File pAnswers = new File("files/pAnswers");
        File caAnswers = new File("files/caAnswers");
        File cpAnswers = new File("files/cpAnswers");

        List<String> mqList = Tools.readFromFile(mQuestions);
        List<String> maList = Tools.readFromFile(mAnswers);
        List<String> pqList = Tools.readFromFile(pQuestions);
        List<String> paList = Tools.readFromFile(pAnswers);

        List<String> caqList = Tools.readFromFile(caQuestions);
        List<String> cpqList = Tools.readFromFile(cpQuestions);
        List<String> caaList = Tools.readFromFile(caAnswers);
        List<String> cpaList = Tools.readFromFile(cpAnswers);

        List<Page> motivationPages = new ArrayList<>();
        List<Page> potentialPages = new ArrayList<>();
        List<Page> caPages = new ArrayList<>();
        List<Page> cpPages = new ArrayList<>();

        setPages(mqList.size(),mqList,maList,motivationPages, TestType.MOTIVATION);
        setPages(pqList.size(),pqList,paList,potentialPages,TestType.POTENTIAL);
        setPages(caqList.size(),caqList,caaList,caPages,TestType.CLARIFYING_A);
        setPages(cpqList.size(),cpqList,cpaList,cpPages,TestType.CLARIFYING_P);

        pagesContainer.setMPages(motivationPages);
        pagesContainer.setPPages(potentialPages);
        pagesContainer.setCaPages(caPages);
        pagesContainer.setCpPages(cpPages);

        nextButton.setOnAction(event -> {

            person.setName(nameField.getText());
            person.setLastName(lastNameField.getText());

            if (fullScreenCheck.isSelected()) { fullScreenOn = true; }

            person.setName(nameField.getText());
            person.setLastName(lastNameField.getText());

            if (motivationAndPotentialButton.isSelected()) { Loader.load("/cards/Page.fxml", pagesContainer, TestType.MOTIVATION, person); }
            if (motivationClarifyingButton.isSelected()) { Tools.clarifyingInit(person, pagesContainer); }

            nextButton.getScene().getWindow().hide();

        });
    }

    private void initializeFields(){
        nextButton.setDisable(true);
        lastNameField.setDisable(true);
        Tools.setOnlyLetters(nameField);
        Tools.setOnlyLetters(lastNameField);
        motivationAndPotentialButton.setSelected(true);
        nameField.textProperty().addListener((observable, oldValue, newValue) -> lastNameField.setDisable(newValue.trim().isEmpty()));
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> nextButton.setDisable(newValue.trim().isEmpty()));
    }

    private void setPages(int questionListSize,List<String> questionsList,List<String> answersList,List<Page> pagesList,TestType type){

        for (int i = 1; i < questionListSize; i++) {

            Page page = new Page();
            page.setQuestion(questionsList.get(i));
            page.setType(type);
            page.setAnswers(makeAnswers(answersList,i+"-"));
            pagesList.add(page);
        }

    }

    private List<String> makeAnswers(List<String> list, String s){

        List<String> temp = new ArrayList<>();

        for (String s1 : list) {
            if (s1.startsWith(s)) temp.add(s1);
        }

        return temp;
    }

}
