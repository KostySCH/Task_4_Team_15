package com.cgvsu;

import com.cgvsu.objreader.ObjReader;
import com.cgvsu.render_engine.RenderEngine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.vecmath.Vector3f;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GUI {
    private Controller controller;
    @FXML
    AnchorPane anchorPane;

    @FXML
    private Canvas canvas;
    private Timeline timeline;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        controller = new Controller();

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
            controller.setAspectRatio((float) (width / height));
            controller.handleRender(canvas.getGraphicsContext2D(), (int) width, (int) height);
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());

        try {
            String fileContent = Files.readString(fileName);
            controller.readMesh(ObjReader.read(fileContent));
            // todo: обработка ошибок
        } catch (IOException exception) {

        }
    }

    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        controller.handleCameraForward();
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        controller.handleCameraBackward();
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        controller.handleCameraLeft();
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        controller.handleCameraRight();
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        controller.handleCameraUp();
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        controller.handleCameraDown();
    }
}
