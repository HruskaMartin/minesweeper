package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;
import minesweeper.core.Tile.State;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;
	private long startMillis = System.currentTimeMillis();

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		Field field = new Field(8, 8, 9);

		System.out.println("Hra zacala");
		 userInterface = new ConsoleUI();
		 userInterface.newGameStarted(field);
	}


	
	public static void main(String[] args) {
		new Minesweeper();
	}
	
	public int getPlayingSeconds() {
		int i = ((int) (System.currentTimeMillis()- startMillis)) ;
		return i;
	}
}
