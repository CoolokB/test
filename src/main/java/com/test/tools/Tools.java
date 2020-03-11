package com.test.tools;

import com.test.Page;
import com.test.Person;
import com.test.enums.TestType;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tools {

    public static List<String> readFromFile(File file) {

        List<String> temp = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                temp.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {

            System.out.println("No files");

        }
        return temp;
    }

    private static void makeAlert() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Допустиме значення додатку 1.1.1 (0 - 26)" + "\n" + "Допустиме значення додатку 1.1.3 (0 - 37)");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/res/Warning.png"));
        alert.showAndWait();

    }

    public static void clarifyingInit(Person p, PagesContainer container) {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Уточнення рівня мотивації");

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/res/Warning.png"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(7);
        grid.setPadding(new Insets(20, 10, 0, 10));

        TextField result111 = new TextField();
        result111.setPromptText("(0 - 26)");
        TextField result113 = new TextField();
        result113.setPromptText("(0 - 37)");

        setOnlyDigits(result111);
        setOnlyDigits(result113);

        ToggleGroup group = new ToggleGroup();

        RadioButton presentButton = new RadioButton();
        presentButton.setText("вакансії присутні");
        presentButton.setToggleGroup(group);
        presentButton.setSelected(true);

        RadioButton absentButton = new RadioButton();
        absentButton.setText("вакансії відсутні");
        absentButton.setToggleGroup(group);

        Button button = new Button("Далі");

        grid.add(new Label("Результат додатку 1.1.1 :"), 0, 0);
        grid.add(result111, 1, 0);
        grid.add(new Label("Результат додатку 1.1.3 :"), 0, 1);
        grid.add(result113, 1, 1);
        grid.add(presentButton, 0, 3);
        grid.add(absentButton, 0, 4);
        grid.add(button, 2, 5);

        button.setDisable(true);
        result113.setDisable(true);

        result111.textProperty().addListener((observable, oldValue, newValue) -> result113.setDisable(newValue.trim().isEmpty()));
        result113.textProperty().addListener((observable, oldValue, newValue) -> button.setDisable(newValue.trim().isEmpty()));

        button.setOnAction(event -> {

            int res111 = Integer.parseInt(result111.getText());
            int res113 = Integer.parseInt(result113.getText());

            if (res111 > 26 || res113 > 37) {
                makeAlert();
            }

            p.setResult111(res111);
            p.setResult113(res113);

            if (presentButton.isSelected()) Loader.load("/cards/Page.fxml", container, TestType.CLARIFYING_P, p);
            if (absentButton.isSelected()) Loader.load("/cards/Page.fxml", container, TestType.CLARIFYING_A, p);

            stage.hide();

        });

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();

    }

    public static void setOnlyLetters(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\D*")) {
                field.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });
    }

    private static void setOnlyDigits(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[^\\D]")) {
                field.setText(newValue.replaceAll("\\D*", ""));
            }
        });
    }

    public static void setButtons(Page page, List<RadioButton> buttons) {

        List<String> answers = page.getAnswers();

        for (int i = 0; i < answers.size(); i++) {

            buttons.get(i).setVisible(true);
            buttons.get(i).setText(answers.get(i));
        }

        buttons.get(0).setSelected(true);
    }

}
