This is my first project, if you were looking for something more professional, this isn't it. In fact, I don't even speak English. I'm a Colombian guy with 15 years old, and I'm translating this with Google Translate.

vJade is a library that allows you to create and configure applications/games (for now only for PC but I plan to expand it to other platforms if possible) powered by modern OpenGL (3.3) thanks to LWJGL

If you're wondering what the v in vJade means, I honestly don't know, it just sounded good, I improvised and said "why not v for visual, because it's designed to make graphic applications and... that... you understand?"

This is a basic example for create a basic window: 
```java
public class Main {
    public static void main(String[] args) {
        Window window = new Window()
                .setBackgroundColor(new ColorRGB(0, 0, 0))
                .setSize(1124, 680)
                .setState(State.CENTERED)
                .setTitle("vJade")

                .setResizable(true)
                .setDecorations(true)
                .setVsync(true)

                .addScreen(new DebugScreenOne(), "debug1")
                .addScreen(new DebugScreenTwo(), "debug2")

                .setMainScreen("debug1");

        window.run();
    }
}
```
If you want to create a screen simply create an extended class with the Screen class. (I won't lie to you, I did this with the help of ChatGPT)

The Screen class has three main methods: start, when a scene starts; update, which is the process that updates itself every frame (I think); and finish, which runs before a scene change (I know this isn't implemented yet, but it should also run before the process's end of life in general).

Don't take this project seriously, but if you give it a try and let me know how it goes, that would be fucking fantastic.

Thx you for reading this.
