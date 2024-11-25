package org.codeforall.AfterMidnight.Game;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MainMenu {

    Picture back;
    public void drawback(){
        Canvas.setMaxX(1920);
        Canvas.setMaxY(1080);
        back = new Picture(0,0, Game.PREFIX + "Images/Backgrounds/MainMenu.jpg");
        back.draw();
    }

}
