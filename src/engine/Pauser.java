package engine;

import java.util.logging.Logger;

/**
 * Implements the paused screen.
 */

public class Pauser {

    protected Logger logger;

    private long pausingTime;

    public Pauser() {

        this.pausingTime = System.currentTimeMillis();
        this.logger = Core.getLogger();

        this.logger.info("Pausing game.");
    }
}
