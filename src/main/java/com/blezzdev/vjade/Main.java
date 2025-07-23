package com.blezzdev.vjade;

import com.blezzdev.vjade.util.color.ColorHSV;
import com.blezzdev.vjade.win.Window;

public class Main {
    public static void main(String[] args) {
        Window win = new Window();

        win.setBackgroundColor(ColorHSV.JADE);
        win.setSize(800, 600);
        win.setTitle("Balatro is life.");

        new DebugScene(win, "menu");

        win.setScene("menu");

        win.run();
    }
}