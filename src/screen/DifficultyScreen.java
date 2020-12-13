package screen;

import engine.Cooldown;
import engine.Core;

import java.awt.event.KeyEvent;

public class DifficultyScreen extends Screen {

    private static final int SELECTION_TIME = 200;

    private Cooldown selectionCooldown;

    protected int returnCode;

    public DifficultyScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        // Defaults easy.
        this.returnCode = 1;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    public final int run() {
        super.run();

        return this.returnCode;
    }

    protected final void update() {
        super.update();

        draw();
        if (this.selectionCooldown.checkFinished()
                && this.inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_UP)
                    || inputManager.isKeyDown(KeyEvent.VK_W)) {
                previousMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                nextItemMenu();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE))
                this.isRunning = false;
        }
    }

    private void nextItemMenu() {
        if (this.returnCode == 3)
            this.returnCode = 1;
        else if (this.returnCode == 1)
            this.returnCode = 2;
        else
            this.returnCode++;
    }

    private void previousMenuItem() {
        if (this.returnCode == 1)
            this.returnCode = 3;
        else if (this.returnCode == 2)
            this.returnCode = 1;
        else
            this.returnCode--;
    }

    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawDifficulty(this);
        drawManager.drawDifficultyMenu(this, this.returnCode);

        drawManager.completeDrawing(this);
    }
}
