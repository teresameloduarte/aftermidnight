package org.codeforall.AfterMidnight.Objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Game.Game;


public class Flash {

    private static int remainingFlashes = 30;

    public static boolean isFlashUsed() {
        return flashUsed;
    }

    private static boolean flashUsed = false;

    private static Picture flash;

    private static Text flashes;


    public void flashIt(){
        new Thread(() -> {
            try {
                activateFlash();
                Thread.sleep(1000);
                deactivateFlash();
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public void activateFlash() {
        if (remainingFlashes > 0) {
            flash = new Picture(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Utils/teste flash.jpg");
            flash.draw();
            flashUsed = true;
            System.out.println("FLASH");
        }
    }

    public void deactivateFlash(){
        flash.delete();
        remainingFlashes--;
        writeFlashes();
        flashUsed = false;
    }

    public static void writeFlashes(){
        if(remainingFlashes == 30){
            flashes = new Text(75, 10, "30 Flashes");
        }else {
            flashes.delete();
            if (remainingFlashes <= 0) {
                flashes = new Text(75, 10, "0 Flashes");
            } else {
                flashes = new Text(75, 10, remainingFlashes + " Flashes");
            }
        }
        flashes.grow(50,20);
        flashes.setColor(Color.WHITE);
        flashes.draw();
    }

}
