package org.codeforall.AfterMidnight.MovementHandler;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Objects.Flashlight;

public class MyMouseHandler implements MouseHandler {

    private static final int MOVE_RIGHT_BOUNDARY = 246;
    private static final int MOVE_LEFT_BOUNDARY = 1517;

    private final Flashlight light;

    private final Mouse mouse;
    private int mouseXMove;

    public MyMouseHandler(Flashlight flashlight){
        this.light = flashlight;
        mouse = new Mouse(this);
        addMouseListeners();
    }

    private void addMouseListeners() {
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    private void updateMouseMoveCoordinates(MouseEvent mouseEvent) {
        mouseXMove = (int) mouseEvent.getX();
    }


    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        updateMouseMoveCoordinates(mouseEvent);
        if (Game.outOfMenu) {
            handleMenuMovement();
        }
        System.out.println(mouseEvent);
    }

    private void handleMenuMovement() {
        if (mouseXMove < MOVE_RIGHT_BOUNDARY && !MainChar.getkilledPlayer()) {
            moveBackgroundRight();
        } else if (mouseXMove > MOVE_LEFT_BOUNDARY && !MainChar.getkilledPlayer()) {
            moveBackgroundLeft();
        }
    }

    private void moveBackgroundRight() {
        if(Game.back.getX() < -10){
            Game.back.translate(20, 0);
            Game.setPicX(Game.getPicX()+20);
            light.moveFlashlight(20);
        }
    }

    private void moveBackgroundLeft() {
        if(Game.back.getX() > -600){
            Game.back.translate(-20, 0);
            Game.setPicX(Game.getPicX()-20);
            light.moveFlashlight(-20);
        }
    }

}

