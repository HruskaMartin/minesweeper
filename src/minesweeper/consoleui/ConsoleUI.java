package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Mine;
import minesweeper.core.Tile;
import minesweeper.core.Tile.State;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads line of text from the reader.
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
    @Override
	public void newGameStarted(Field field) {
        this.field = field;
        do {
            update();
            processInput();
            /*
            if(field.getState == GameState.SOLVED) {
            	System.exit(0);
            } else {
            	System.exit(0);
            }*/
        } while(true);
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
    @Override
	public void update() {
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
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */

    	private void processInput() {
    		
    		String pattern = ("[OM]{1}([A-I])([0-8])");
    		String input = readLine();
    		
    		Pattern p = Pattern.compile(pattern);
    		Matcher matcher = p.matcher(input); 		
    		matcher.matches();
    		
    		if(!matcher.matches()) {
    			System.out.println("zadajte novy vstup");
    			return;
    		}  
    		
    		if(matcher.matches()) {
    			String a = matcher.group(1);
    			String row = matcher.group(2);
    			String column = matcher.group(3);
    			int value = Character.getNumericValue(row.charAt(0)) -10;
    			
    			if(a.equals('O')) {
    				field.openTile(value, Integer.parseInt(column));
    				return;
    			} else if(a.equals('M')) {
    				field.markTile(value, Integer.parseInt(column));
    				return;
    			}
    			
    		}
    		
    	}
}
