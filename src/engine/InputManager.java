package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages keyboard input for the provided screen.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public final class InputManager implements KeyListener {

	/** Number of recognised keys. */
	private static final int NUM_KEYS = 256;
	/** Array with the keys marked as pressed or not. */
	private static boolean[] keys;
	/** Singleton instance of the class. */
	private static InputManager instance;
	/** Array with the keys marked as toggled or not. */
	private static boolean[] toggles;
	/** Array with the keys marked as toggled or not. */
	private static boolean[] toggleHelper;

	/**
	 * Private constructor.
	 */
	private InputManager() {
		keys = new boolean[NUM_KEYS];
		toggles = new boolean[NUM_KEYS];
		toggleHelper = new boolean[NUM_KEYS];
		resetToggles();
	}

	/**
	 * Returns shared instance of InputManager.
	 * 
	 * @return Shared instance of InputManager.
	 */
	protected static InputManager getInstance() {
		if (instance == null)
			instance = new InputManager();
		return instance;
	}

	/**
	 * Returns true if the provided key is currently pressed.
	 * 
	 * @param keyCode
	 *            Key number to check.
	 * @return Key state.
	 */
	public boolean isKeyDown(final int keyCode) {
		return keys[keyCode];
	}

	/**
	 * Changes the state of the key to pressed.
	 * 
	 * @param key
	 *            Key pressed.
	 */
	@Override
	public void keyPressed(final KeyEvent key) {
		if (key.getKeyCode() >= 0 && key.getKeyCode() < NUM_KEYS) {
			keys[key.getKeyCode()] = true;
			if (!toggleHelper[key.getKeyCode()] && !toggles[key.getKeyCode()]) toggles[key.getKeyCode()] = true;
			else if (toggleHelper[key.getKeyCode()] && toggles[key.getKeyCode()]) toggles[key.getKeyCode()] = false;
		}
	}

	/**
	 * Changes the state of the key to not pressed.
	 * 
	 * @param key
	 *            Key released.
	 */
	@Override
	public void keyReleased(final KeyEvent key) {
		if (key.getKeyCode() >= 0 && key.getKeyCode() < NUM_KEYS) {
			keys[key.getKeyCode()] = false;
			if (!toggleHelper[key.getKeyCode()] && toggles[key.getKeyCode()]) toggleHelper[key.getKeyCode()] = true;
			else if (toggleHelper[key.getKeyCode()] && !toggles[key.getKeyCode()]) toggleHelper[key.getKeyCode()] = false;
		}
	}

	/**
	 * Does nothing.
	 * 
	 * @param key
	 *            Key typed.
	 */
	@Override
	public void keyTyped(final KeyEvent key) {

	}

	/**
	 * Returns true if the provided key is currently toggled.
	 *
	 * @param keyCode
	 *            Key typed.
	 * @return toggle state.
	 */
	public boolean isToggled(final int keyCode) { return toggles[keyCode]; }

	/**
	 * Reset all toggles off.
	 */
	public void resetToggles() {
		for (int i = 0; i < NUM_KEYS; i++) {
			toggles[i] = false;
			toggleHelper[i] = false;
		}
	}
}