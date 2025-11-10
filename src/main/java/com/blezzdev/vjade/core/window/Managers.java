package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.UserInterfaceManager;
import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.core.manager.collider.CollisionManager;
import com.blezzdev.vjade.core.manager.input.InputManager;
import com.blezzdev.vjade.core.manager.timer.TimerManager;

/** The {@code Managers} class is a <b>control class</b> dedicated
 * to managing different managers responsible for specific tasks.
 * <p>Here is a list of the current managers.</p>
 * <ul>
 *     <li><b>User interface manager</b> manages the internal tasks of the UIs.</li>
 *     <li><b>Collision manager</b> manages collision internal tasks.</li>
 *     <li><b>Screen manager</b> manages the life-cycle screens.</li>
 *     <li><b>Canvas manager</b> manages texture batching.</li>
 *     <li><b>Timer manager</b> manages delay actions.</li>
 *     <li><b>Input manager</b> manages inputs of the keyboard, mouse and external controllers.</li>
 * </ul>
 *
 * @author Alfaro
 * @version 1
 * @since vJade 1
 * */

public class Managers {
    private final UserInterfaceManager userInterface = new UserInterfaceManager();
    private final CollisionManager collision = new CollisionManager();
    private final ScreenManager screen = new ScreenManager();
    private final CanvasManager canvas = new CanvasManager();
    private final TimerManager timer = new TimerManager();
    private final InputManager input = new InputManager();

    public void start() {
        screen.init();
        input.init();
    }

    public void update(float deltaTime) {
        screen.update(deltaTime);
        timer.update(deltaTime);

        canvas.update(deltaTime, screen);
        input.update();
    }

    public void end() {
        screen.destroy();
        timer.clear();
        canvas.clear();
    }

    public InputManager getInput() {
        return input;
    }
    public CollisionManager getCollision() {
        return collision;
    }
    public UserInterfaceManager getUserInterface() {
        return userInterface;
    }
    public ScreenManager getScreen() {
        return screen;
    }
    public TimerManager getTimer() {
        return timer;
    }
    public CanvasManager getCanvas() { return canvas; }
}
