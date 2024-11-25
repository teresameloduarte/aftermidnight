package org.codeforall.AfterMidnight.Game;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Game {

    public static boolean outOfMenu = false;

    private static int picX;

    private static int picY;

    private static String path;

    public static String lastImg;

    public static Picture back;

    public static final String PREFIX = "" ;

    public static int getPicX() {
        return picX;
    }

    public static int getPicY() {
        return picY;
    }

    public static void setPicX(int picX) {
        Game.picX = picX;
    }

    public void run() {
    }

    public static void draw(int X, int Y, String caminho){
        picX = X;
        picY = Y;
        lastImg = path;
        path = caminho;
        back = new Picture(X, Y, path);
        back.draw();
    }


    public static void delete(){
        back.delete();
    }


}
