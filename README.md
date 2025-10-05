> Developer's note: This is my first real project. I am still new to the world of formal development, as I am a minor guy, so I would greatly appreciate any recommendations or bug reports, as they would help me expand my knowledge and improve aspects of this framework. Thank you for reading this note.

## About vJade
vJade is an open-source framework for Java (and, consequently, Kotlin), focused on creating graphical applications, video games, and interactive systems. Designed to offer an architecture that prioritizes:
- **Simplicity** for quick handling.
- **Customization** for a tailor-made program.
- **Modularity**, for easy development.
- **Scalability**, to create large-scale projects.

## Libraries Used
This project works thanks to many of the features provided by the following libraries:<br/>
- **Lwjgl** (with OpenGL 3.3 control for PC).
- **Jolm** (with the Shader calculations).<br/>

## Learning vJade
vJade provides detailed documentation on what, how, and when to use the tools offered by this framework for any goal you pursue. Within the classes themselves, in IntelliJ IDEA Community Edition (or the paid version), you can maintain control while hovering over a class to view its contents and consult the documentation integrated into the header.

If you need more organized information or have questions, you can consult this [vJade documentation](https://docs.google.com/document/d/14sKEtOS4JkRvfoYuMk0H5xItIE_ZHU22XgfBQa_9jGM/edit?usp=sharing) or ask directly on the forum.

## Quick Start
Here is a quick guide on how to use vJade if you need something quick that works.
To begin with, you need to create the screens that can be displayed during your program's lifecycle. All you need to do is create a class that extends the Screen class, for example:

```java
public class GameScreen extends Screen {
    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
```
Within the classes that inherit from the Screen class, there are methods that are executed at certain points in a program's lifetime, which are found in the following hierarchy:
- **start** runs when the life cycle of a scene begins.
- **update** runs every frame.
- **end_scene** runs before the screen change.
- **finish** runs before the screen change or when a program's lifetime ends.
- **end_program** runs when a program's lifetime ends.

To initialize a program you will need to instantiate an Engine class, configure it, and execute it, preferably within the initialization method, for example:
```java
public class Main {
    public static void main(String[] args) {
        Engine game = new Engine()
                .setBackgroundColor(new ColorRGB(255, 255, 255))

                .setTitle("vJade Program")
                .setSize(1124, 680)
                .setState(Window.State.CENTERED)

                .setResizable(false)

                // As the first argument to refer to the screen and
                // as the second argument to create an identifier for it.
                .addScreen(ScreenGame::new, "debug")

                // You can omit the identifier, as it will be created automatically with the class name.
                .addScreen(ScreenMenu::new)

                .setMainScreen("debug");

        game.launch();
    }
}
```
