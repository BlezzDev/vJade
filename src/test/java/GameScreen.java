import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.manager.input.InputManager;
import com.blezzdev.vjade.core.manager.input.Keyboard;
import com.blezzdev.vjade.core.manager.screen.Screen;

public class GameScreen extends Screen {
    public GameScreen(GameApplication application) {
        super(application);
    }

    int lastFPS = 0;

    @Override
    protected void start() {

    }

    @Override
    protected void update() {
        if (getManager(InputManager.class).isKeyJustDown(Keyboard.SPACE)) {
            System.out.println("Hello world " + lastFPS);
            lastFPS++;
        }
    }

    @Override
    protected void end() {

    }

    @Override
    protected void endScreen() {

    }

    @Override
    protected void endProgram() {

    }
}