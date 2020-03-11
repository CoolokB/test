package com.test.tools;

import com.test.MainController;
import com.test.Page;
import com.test.Person;
import com.test.enums.TestType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class Loader {

    public static void load(String path, PagesContainer container, TestType type, Person person) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Loader.class.getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Inter controller = loader.getController();
        controller.initialize(container, type, person);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Тестування");
        stage.getIcons().add(new Image("/res/test3.png"));
        if (MainController.fullScreenOn) {
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        }

        stage.show();

    }

    public static void load(String path, Person person) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Loader.class.getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Inter controller = loader.getController();
        controller.initialize(person);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Тестування");
        stage.getIcons().add(new Image("/res/test3.png"));
        if (MainController.fullScreenOn) {
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        }

        stage.show();

    }

    public static void load(String path, List<Page> pages, Person person) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Loader.class.getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Inter controller = loader.getController();
        controller.initialize(pages, person);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Тестування");
        stage.getIcons().add(new Image("/res/test3.png"));
        if (MainController.fullScreenOn) {
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        }

        stage.show();

    }

}
