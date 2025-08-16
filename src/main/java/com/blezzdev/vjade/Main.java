package com.blezzdev.vjade;

import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.tools.color.ColorRGB;

public class Main {
    public static void main(String[] args) {
        Window win = new Window();
        win.setSize(1124, 680);
        win.setTitle("Balatrin");
        win.setResizable(true);
        win.setDecorations(true);
        win.setBackgroundColor(ColorRGB.BROWN);

        win.run();
    }
}