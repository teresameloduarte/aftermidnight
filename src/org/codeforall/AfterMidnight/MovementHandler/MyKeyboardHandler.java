package org.codeforall.AfterMidnight.MovementHandler;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Game.Music;
import org.codeforall.AfterMidnight.Game.Time;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flash;
import org.codeforall.AfterMidnight.Objects.Flashlight;


public class MyKeyboardHandler implements KeyboardHandler {
    private final Keyboard keyboard;
    private final Flashlight flashlight;
    private final Flash flash;
    private final MainChar tz;

    private Music guide;
    private final Door door;

    private boolean firstImage = false;

    private boolean secondImage = false;


    public MyKeyboardHandler(Flashlight flashlight, Flash flash, MainChar TZ, Door door) {
        keyboard = new Keyboard(this);
        setKeyboardEvents();
        this.flashlight = flashlight;
        this.flash = flash;
        this.tz = TZ;
        this.door = door;
    }

    private void setKeyboardEvents() {
        KeyboardEvent flashOn = new KeyboardEvent();
        KeyboardEvent flashOff = new KeyboardEvent();
        flashOn.setKey(KeyboardEvent.KEY_SHIFT);
        flashOff.setKey(KeyboardEvent.KEY_SHIFT);
        flashOn.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        flashOff.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(flashOn);
        keyboard.addEventListener(flashOff);

        KeyboardEvent flash = new KeyboardEvent();
        flash.setKey(KeyboardEvent.KEY_SPACE);
        flash.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(flash);

        KeyboardEvent doorClose = new KeyboardEvent();
        doorClose.setKey(KeyboardEvent.KEY_D);
        doorClose.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(doorClose);

        KeyboardEvent select = new KeyboardEvent();
        select.setKey(KeyboardEvent.KEY_ENTER);
        select.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(select);

        KeyboardEvent skipGuide = new KeyboardEvent();
        skipGuide.setKey(KeyboardEvent.KEY_X);
        skipGuide.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(skipGuide);

        KeyboardEvent exit = new KeyboardEvent();
        exit.setKey(KeyboardEvent.KEY_ESC);
        exit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(exit);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC){
            System.exit(0);
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_X && firstImage && guide.isPlaying()){
            guide.setPlaying(false);
            guide.stop();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SHIFT && Game.outOfMenu && !MainChar.getkilledPlayer()) {
            flashlight.activateFlashlight();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE && Game.outOfMenu && !MainChar.getkilledPlayer()) {
            flash.flashIt();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_D && Game.outOfMenu && !MainChar.getkilledPlayer()) {
            door.closeIt();
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER && !Game.outOfMenu && !firstImage && !secondImage) {
            Game.draw(0,0,Game.PREFIX + "Images/Backgrounds/Nights_F_news.jpg");
            guide =  new Music(Game.PREFIX + "guide.wav");
            guide.setPlaying(true);
            guide.play(true);
            firstImage = true;
        }else if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER && !Game.outOfMenu && firstImage && !secondImage) {
            Game.delete();
            Game.draw(0,0,Game.PREFIX + "Images/Backgrounds/IMG_3703.JPG");
            firstImage = false;
            secondImage = true;
        }else if(keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER && !Game.outOfMenu && secondImage) {
            guide.stop();
            Game.delete();
            Game.draw(-600, 0, Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            Game.outOfMenu = true;
            Flash.writeFlashes();
            openBackground();
            secondImage = false;
        }

    }

    private void openBackground(){

        Time time = new Time();
        time.timer(tz);
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_SHIFT && Game.outOfMenu){
            flashlight.deactivateFlashlight();
        }
    }
}


