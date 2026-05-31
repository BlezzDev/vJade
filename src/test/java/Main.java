import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.core.manager.input.InputManager;
import com.blezzdev.vjade.core.manager.screen.ScreenManager;
import com.blezzdev.vjade.core.manager.time.TimeManager;
import com.blezzdev.vjade.core.window.WindowHelper;
import com.blezzdev.vjade.util.canvas.Icon;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        GameApplication app = new GameApplication("Neptune", 1124, 680);
        Logger logger = Logger.getLogger("Neptune");

        app.addLogger(logger).getManagers()
                .register(new ScreenManager(app))
                .register(new TimeManager(app))
                .register(new CanvasManager(app))
                .register(new InputManager(app))
        ;

        app.getManagers().get(ScreenManager.class)
                .registerScreen("game", () -> new GameScreen(app))

                .setMainScreen("game")
        ;

        app.getWindow().getProperties()
                .setIcon(new Icon("vjade_icon.png"))
        ;

        WindowHelper.with(app.getWindow()).centered();

        app.launch();
    }
}
