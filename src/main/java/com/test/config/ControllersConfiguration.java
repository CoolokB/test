package com.test.config;

import com.test.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ControllersConfiguration {

    @Bean
    public ViewHolder getMainView() throws IOException {
        return loadView("cards/MainScreen.fxml");
    }

    @Bean
    public MainController getMainController() throws IOException {
        return getMainView().getController();
    }

    @Bean(name = "mainParent")
    Parent mainParent() throws IOException {
        return getMainView().getView();
    }

    protected ViewHolder loadView(String path) throws IOException {
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream(path)) {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane pane = loader.load(fxmlStream);

            return new ViewHolder(loader.getRoot(), loader.getController(), pane);
        }
    }

    @Data
    @AllArgsConstructor
    public static class ViewHolder {
        private Parent view;
        private MainController controller;
        private AnchorPane pane;
    }
}


