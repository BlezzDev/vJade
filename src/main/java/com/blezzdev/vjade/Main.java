package com.blezzdev.vjade;

import com.blezzdev.vjade.util.color.VJadeColorHSV;
import com.blezzdev.vjade.win.VJadeWindow;

public class Main {
    public static void main(String[] args) {
        VJadeWindow win = new VJadeWindow(1124, 680, "Balatro");
        win.setBackgroundColor(VJadeColorHSV.WHITE);

        new EscenaMenu(win, "menu");

        win.setScene("menu");

        win.run();
    }
}