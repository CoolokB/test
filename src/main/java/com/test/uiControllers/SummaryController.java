package com.test.uiControllers;

import com.test.*;
import com.test.enums.TestType;
import com.test.tools.Inter;
import com.test.tools.Loader;
import com.test.tools.ProfileInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SummaryController implements Inter {

    @FXML private Label name;
    @FXML private Label lastName;
    @FXML private Label profileInfo;
    @FXML private Label profile;
    @FXML private Label profileDescription;
    @FXML private Button next;

    public Label answer1;
    public Label answer2;
    public Label answer3;
    public Label answer4;
    public Label answer5;
    public Label answer6;
    public Label answer7;
    public Label answer8;
    public Label answer9;
    public Label answer10;
    public Label answer11;
    public Label answer12;
    public Label answer13;
    public Label answer14;
    public Label answer15;
    public Label answer16;
    public Label answer17;
    public Label answer18;
    public Label answer19;
    public Label answer20;
    public Label answer21;

    private Person person;
    private int profileNumber;

    public void initialize(List<Page> pages, Person person) {

        this.person = person;

        name.setText(person.getName());
        lastName.setText(person.getLastName());

        AtomicInteger motivationPoints = new AtomicInteger();
        AtomicInteger potentialPoints = new AtomicInteger();

        pages.forEach(p -> {
            if (p.getType() == TestType.POTENTIAL) potentialPoints.addAndGet(p.getPoints());
            if (p.getType() == TestType.MOTIVATION) motivationPoints.addAndGet(p.getPoints());
        });

        profileNumber = ProfileInfo.getProfile(motivationPoints.get(), potentialPoints.get());

        profile.setText(String.valueOf(profileNumber));

        List<Label> labels = Arrays.asList(answer1, answer2, answer3, answer4, answer5, answer6, answer7,
                answer8, answer9, answer10, answer11, answer12, answer13, answer14, answer15,
                answer16, answer17, answer18, answer19, answer20, answer21);

        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setText(pages.get(i).getAnswer());
        }

        setProfileDescription();

        person.setProfile(profileNumber);

        next.setOnAction(event -> {
            next.getScene().getWindow().hide();
            loadNext();
        });

    }

    private void loadNext() {
        switch (profileNumber) {

            case 1:
                Loader.load("/cards/profiles/profile1.fxml", person);
                break;
            case 2:
                Loader.load("/cards/profiles/profile2.fxml", person);
                break;
            case 3:
                Loader.load("/cards/profiles/profile3.fxml", person);
                break;
            case 4:
                Loader.load("/cards/profiles/profile4.fxml", person);
                break;
            case 5:
                Loader.load("/cards/profiles/profile5.fxml", person);
                break;
        }
    }

  private void setProfileDescription() {
        switch (profileNumber) {
            case 1:
                profileDescription.setText(ProfileInfo.profile1Description);
                break;
            case 2:
                profileInfo.setText(ProfileInfo.profile2Info);
                profileDescription.setText(ProfileInfo.profile2Description);
                break;
            case 3:
                profileInfo.setText(ProfileInfo.profile3Info);
                profileDescription.setText(ProfileInfo.profile3Description);
                break;
            case 4:
                profileInfo.setText(ProfileInfo.profile4Info);
                profileDescription.setText(ProfileInfo.profile4Description);
                break;
            case 5:
                profileInfo.setText(ProfileInfo.profile5Info);
                profileDescription.setText(ProfileInfo.profile5Description);
                break;
        }
    }

}
