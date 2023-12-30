package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.render_engine.Camera;
import com.cgvsu.render_engine.RenderEngine;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import javax.vecmath.Vector3f;

public class Controller {
    final private float TRANSLATION = 0.5F;

    private Model mesh = null;

    private Camera camera = new Camera(
            new Vector3f(0, 0, 100),
            new Vector3f(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    public void readMesh(Model mesh) {
        this.mesh = mesh;
    }
    public void handleRender(GraphicsContext graphicsContext, int width, int height) {
        if (mesh != null) {
            RenderEngine.render(graphicsContext, camera, mesh, width, height);
        }

    }

    public void setAspectRatio(float value) {
        camera.setAspectRatio(value);
    }

    public void handleCameraForward() {
        camera.movePosition(new Vector3f(0, 0, -TRANSLATION));
        //camera.moveTarget(new Vector3f(0, 0, -TRANSLATION));
    }

    public void handleCameraBackward() {
        camera.movePosition(new Vector3f(0, 0, TRANSLATION));
        //camera.moveTarget(new Vector3f(0, 0, TRANSLATION));
    }

    public void handleCameraLeft() {
        camera.movePosition(new Vector3f(TRANSLATION, 0, 0));
        //camera.moveTarget(new Vector3f(TRANSLATION, 0, 0));
    }

    public void handleCameraRight() {
        camera.movePosition(new Vector3f(-TRANSLATION, 0, 0));
        //camera.moveTarget(new Vector3f(-TRANSLATION, 0, 0));
    }

    public void handleCameraUp() {
        camera.movePosition(new Vector3f(0, TRANSLATION, 0));
        //camera.moveTarget(new Vector3f(0, TRANSLATION, 0));
    }

    public void handleCameraDown() {
        camera.movePosition(new Vector3f(0, -TRANSLATION, 0));
        //camera.moveTarget(new Vector3f(0, -TRANSLATION, 0));
    }
}
