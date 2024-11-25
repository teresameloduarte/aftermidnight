package org.codeforall.AfterMidnight.Objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Game.Time;

public class Door {

    public static boolean isIsClosed() {
        return isClosed;
    }

    private static boolean isClosed = false;

    private Picture door;

    public void closeIt() {
        new Thread(() -> {
            try {
                if(MainChar.getWhereIsHe() == 1){
                    door = new Picture(0, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_Fz_closedDoor.jpg");
                }else{
                    door = new Picture(0, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_closed.jpg");
                }
                door.draw();
                System.out.println("Door closed");
                isClosed = true;
                Flash.writeFlashes();
                Time.drawHour();
                Thread.sleep(1000);
                door.delete();
                Game.draw(0, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
                isClosed = false;
                Flash.writeFlashes();
                Time.drawHour();
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

}