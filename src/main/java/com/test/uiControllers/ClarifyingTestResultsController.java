package com.test.uiControllers;

import com.test.tools.Inter;
import com.test.tools.MyPrinter;
import com.test.Page;
import com.test.Person;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

public class ClarifyingTestResultsController implements Inter {

    public AnchorPane vAbsentPane;
    public Label surname;
    public Label motivationLevel;
    public Label newMotivationLevel;
    public Label firstQuestion;
    public Label secondQuestion;
    public Label thirdQuestion;
    public Label fourthQuestion;
    public Label fifthQuestion;
    public Label firstAnswer;
    public Label secondAnswer;
    public Label thirdAnswer;
    public Label fourthAnswer;
    public Label fifthAnswer;
    public Label motivationDescription;
    public Label profileLabel;
    public Label profileDescription;
    public Label previousMotivationLevel;
    public Button vAbsentPrint;

    private static boolean newMotivationLow;
    private static boolean newMotivationMid;
    private static boolean newMotivationHigh;

    private static final String profile1Info;
    private static final String profile2Info;
    private static final String profile3Info;
    private static final String profile4Info;
    private static final String profile5Info;

    private static final String mLowText;
    private static final String mMidText;
    private static final String mHighText;

    @Autowired
    MyPrinter printer;

    private int profile;
    private String motivationText;

    static {

        mHighText = "Високий рівень мотивації до працевлаштування";
        mMidText = "Середній рівень мотивації до працевлаштування";
        mLowText = "Низький рівень мотивації до працевлаштування";

        profile1Info = "Високий рівень мотивації до працевлаштування" + "\n" + "і високий потенціал працевлаштуання";
        profile2Info = "Високий рівень мотивації до працевлаштування" + "\n" + "та середній або низький потенціал працевлаштування";
        profile3Info = "Середній або низький рівень мотивації до працевлаштування" + "\n" + "та середній або низький потенціал працевлаштування";
        profile4Info = "Низький або середній рівень мотивації до працевлаштування " + "\n" + "та високий потенціал працевлаштування";
        profile5Info = "Низький рівень мотивації до працевлаштування " + "\n" + "та низький потенціал працевлаштування";
    }

    public void initialize(List<Page> pages, Person person) {

        initializeInfo(person,pages);
        setNM(newMotivationHigh,newMotivationMid,newMotivationLow,motivationDescription);
        setP(profile,profileLabel,profileDescription);
        vAbsentPrint.setOnAction(event -> printer.print(vAbsentPrint,vAbsentPane));

    }

    private void initializeInfo(Person person,List<Page> pages) {

        int points111 = person.getResult111();
        int points113 = person.getResult113();

        int result = 0;

        List<Label> labels = Arrays.asList(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer);
        List<Label> labels1 = Arrays.asList(firstQuestion, secondQuestion, thirdQuestion, fourthQuestion, fifthQuestion);

        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setText(pages.get(i).getAnswer());
            result += pages.get(i).getPoints();
            labels1.get(i).setText(pages.get(i).getQuestion());
        }

        boolean potentialLow = points113 < 16;
        boolean potentialMid = points113 > 15 && points113 < 26;
        boolean potentialHigh = points113 > 25;

        int newResult = result + points111;

        newMotivationLow = newResult < 18;
        newMotivationMid = newResult > 17 && newResult < 27;
        newMotivationHigh = newResult > 26;

        if (result < 4) { motivationText = mLowText; }
        if (result > 3 && result < 8) { motivationText = mMidText; }
        if (result > 7 && result < 11) { motivationText = mHighText; }
        if (newMotivationHigh && potentialHigh) { profile = 1; }
        if (newMotivationHigh && points113 < 26) { profile = 2; }
        if ((newMotivationLow && potentialMid) || (newMotivationMid && potentialLow) || (potentialMid && newMotivationMid) || (newMotivationLow && potentialLow)) { profile = 3; }
        if (newResult < 27 && potentialHigh) { profile = 4; }
        if (newMotivationLow && potentialLow) { profile = 5; }

        surname.setText(person.getName() + " " + person.getLastName());
        motivationLevel.setText("(" + points111);
        newMotivationLevel.setText("- " + "(" + newResult + " балів)");
        previousMotivationLevel.setText("(" + motivationText + ")");
    }
   private static void setNM(boolean newMotivationHigh, boolean newMotivationMid, boolean newMotivationLow, Label motivationDescription) {

        if (newMotivationHigh) { motivationDescription.setText(mHighText); }
        if (newMotivationMid) { motivationDescription.setText(mMidText); }
        if (newMotivationLow) { motivationDescription.setText(mLowText); }

    }

    private static void setP(int profile, Label profileLabel, Label profileDescription) {

        switch (profile) {
            case 1: {
                profileLabel.setText("профіль - " + profile);
                profileDescription.setText(profile1Info);
                break;
            }
            case 2: {
                profileLabel.setText("профіль - " + profile);
                profileDescription.setText(profile2Info);
                break;
            }
            case 3: {
                profileLabel.setText("профіль - " + profile);
                profileDescription.setText(profile3Info);
                break;
            }
            case 4: {
                profileLabel.setText("профіль - " + profile);
                profileDescription.setText(profile4Info);
                break;
            }
            case 5: {
                profileLabel.setText("профіль - " + profile);
                profileDescription.setText(profile5Info);
                break;
            }
        }
    }
}
