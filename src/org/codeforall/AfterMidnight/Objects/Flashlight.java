package org.codeforall.AfterMidnight.Objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Game.Game;

public class Flashlight {

    private final Picture flashlight;

    public static boolean isFlashlightOn() {
        return flashlightOn;
    }

    private static boolean flashlightOn = false;

    public Flashlight(){
        flashlight = new Picture(Game.getPicX()-600,0,
                Game.PREFIX + "Images/Utils/beam_of_light.png");
    }

    public void activateFlashlight() {
        flashlightOn = true;
        flashlight.draw();
    }

    public void deactivateFlashlight() {
        flashlightOn = false;
        flashlight.delete();
    }

    public void moveFlashlight(int move){
        flashlight.translate(move, 0);
    }

}
