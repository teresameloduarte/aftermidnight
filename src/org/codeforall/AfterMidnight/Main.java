package org.codeforall.AfterMidnight;


import org.codeforall.AfterMidnight.Enemies.MainChar;
import org.codeforall.AfterMidnight.Game.Game;
import org.codeforall.AfterMidnight.Game.MainMenu;
import org.codeforall.AfterMidnight.MovementHandler.MyKeyboardHandler;
import org.codeforall.AfterMidnight.MovementHandler.MyMouseHandler;
import org.codeforall.AfterMidnight.Objects.Door;
import org.codeforall.AfterMidnight.Objects.Flash;
import org.codeforall.AfterMidnight.Objects.Flashlight;

public class Main {
    public static void main(String[] args) {
        Game AM =  new Game();
        MainMenu menu = new MainMenu();
        Flashlight flashlight = new Flashlight();
        Flash flash = new Flash();
        Door door = new Door();
        MainChar TZ = new MainChar();
        new MyMouseHandler(flashlight);
        new MyKeyboardHandler(flashlight, flash, TZ, door);
        menu.drawback();
        AM.run();






    }
}


