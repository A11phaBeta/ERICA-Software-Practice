package screen;

import engine.GameSettings;
import engine.GameState;

import java.awt.event.KeyEvent;

public class PauseScreen extends Screen{
    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width  Screen width.
     * @param height Screen height.
     * @param fps
     * @param gs
     */

    private GameScreen gs;
    public PauseScreen(int width, int height, int fps) {
        super(width, height, fps);


    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public int run() {
        super.run();
        return this.returnCode;
    }

    @Override
    protected void update() {
        super.update();

        draw();
        if (this.inputDelay.checkFinished()) {
            if(inputManager.isKeyDown(KeyEvent.VK_ENTER)){
                System.out.println("Enter");
                this.returnCode = 2;
                this.isRunning = false;
            }else{
                System.out.println("ESC");
                this.returnCode = 1;
                this.isRunning = false;
            }
        }
    }

    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawHorizontalLine(this, this.height / 2 - this.height
                / 10);
        drawManager.drawHorizontalLine(this, this.height / 2 + this.height
                / 5);
        drawManager.drawPause(this);

        drawManager.completeDrawing(this);
    }
}
