package org.codeforall.AfterMidnight.Game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flash;
import org.codeforall.AfterMidnight.Objects.Flashlight;


public class Time {
    final boolean[] running = {true};

    private static boolean isDrawn = false;

    private static String hour = "12 AM";

    private long count = 0;
    long gameTimeInMillis = 6 * 60 * 1000; //// 6 minutes in milliseconds

    private static Text Hour;


    public void timer(MainChar tz) {

        // Creating a new thread to manage the game time
        new Thread(() -> {
            try {
                drawHour();
                long startTime = System.currentTimeMillis();
                while (running[0]) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    int remainingTime = (int) (gameTimeInMillis - elapsedTime) / 1000; // Remaining time in seconds
                    if(remainingTime <= 345) {
                        if (!MainChar.isIsSpawned()) {
                            tz.spawnChance();
                        } else {
                            if (counter(tz) == 3) {
                                break;
                            }
                        }
                    }

                    if(remainingTime % 60 == 0 && remainingTime < 360){
                        switch (remainingTime){
                            case 300:
                                hour = "1 AM";
                                drawHour();
                                break;
                            case 240:
                                hour = "2 AM";
                                drawHour();
                                break;
                            case 180:
                                hour = "3 AM";
                                drawHour();
                                break;
                            case 120:
                                hour = "4 AM";
                                drawHour();
                                break;
                            case 60:
                                hour = "5 AM";
                                drawHour();
                                break;
                        }
                        tz.increaseDif();
                        System.out.println(tz.getDifficulty());
                    }

                    if (elapsedTime >= gameTimeInMillis) {
                        Game.outOfMenu = false;
                        Game.draw(0, 0, Game.PREFIX + "Images/Backgrounds/Last.jpg");
                        running[0] = false;
                        break;
                    }

                    // Optionally, you can print the remaining time to the console
                    //long remainingTime = (gameTimeInMillis - elapsedTime) / 1000; // Remaining time in seconds
                    System.out.println("Time left: " + remainingTime + " seconds");
                    Thread.sleep(1000); // Sleep for 1-second to update every second
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public int counter(MainChar tz){
        long fiveSec = 5;
        long sevenSec = 7;
        if(defenseSuccess()){
            count = 0;
            return 1;
        }else if(count == fiveSec){
            tz.jumpscare();
            count++;
            return 2;
        }else if(count >= sevenSec) {
            gameOver();
            return 3;
        }else{
            count++;
            return 4;
        }
    }

    public void gameOver(){
        Picture over = new Picture(0,0, Game.PREFIX + "Images/Backgrounds/ded.png");
        System.out.println("DEAD");
        over.draw();
    }


    public boolean defenseSuccess(){
        if(MainChar.getWhereIsHe() == 1 && Door.isIsClosed()){
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(-9, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            drawHour();
            return true;
        }
        if(MainChar.getWhereIsHe() == 2 && Flash.isFlashUsed()) {
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(Game.getPicX(), Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            drawHour();
            return true;
        }
        if(MainChar.getWhereIsHe() == 3 && Flashlight.isFlashlightOn()){
            MainChar.setIsSpawned(false);
            Game.delete();
            Game.draw(-599, Game.getPicY(), Game.PREFIX + "Images/Backgrounds/BackgroundAM_opened.jpg");
            drawHour();
            return true;
        }
        return false;
    }

    public static void drawHour(){
        if(isDrawn){
            isDrawn = false;
            Hour.delete();
        }
        Hour = new Text(1800, 20, hour);
        Hour.setColor(Color.WHITE);
        Hour.grow(50,20);
        Hour.draw();
        isDrawn = true;
    }


}

