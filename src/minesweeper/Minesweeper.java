package minesweeper;

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
	private UserInterface UserInterface;

	/**
	 * Constructooor.
	 */
	private Minesweeper() {
		Field field = new Field(10, 10, 9);
		printField(field);
		// userInterface = new ConsoleUI();
		// userInterface.newGameStarted(field);
	}

	private void printField(Field field) {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				tile.setState(State.OPEN);
				if (tile.getState() == Tile.State.OPEN) {
					if (tile instanceof Mine) {
						System.out.print("X");
					} else if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
					}
				} else if (tile.getState() == Tile.State.MARKED) {
					System.out.print("M");
				} else if (tile.getState() == Tile.State.CLOSED) {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}

	
	public static void main(String[] args) {
		new Minesweeper();
	}
}
