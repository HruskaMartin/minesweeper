package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.File;
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
            
            if(field.getState() == GameState.SOLVED) {
            	System.out.println("Hra uspesne ukoncena");
            	System.exit(0);
            } 
            if(field.getState() == GameState.FAILED) {
            	System.out.println("Hra neuspesna");
            	System.exit(0);
            }
           
        } while(true);
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
    @Override
	public void update() {
    	System.out.println("Pocet ostavajucich min: " + field.getRemainingMineCount());
    	for(int i = 0; i < field.getColumnCount(); i++) {
    		System.out.printf("\t%d",i);
    	}  	System.out.println();
    	for (int row = 0; row < field.getRowCount(); row++) {
    		System.out.printf("%c\t", row + 65);
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
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
			 System.out.print("\t");
			}
			System.out.println();
		}
    }
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */

    	private void processInput() {
    		
    		System.out.println("Zadajte vstup: ");
    		String input = readLine();
    		input = input.trim().toUpperCase();
    		
    		
    		try {
				handleInput(input);
			} catch (WrongFormatException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
    		
    	
    		
    	}
    	
    	
    	private void handleInput(String input) throws WrongFormatException{

    		String pattern = ("([OM]{1})([A-Z])([0-9])");
    		
    		Pattern p = Pattern.compile(pattern);
    		Matcher matcher = p.matcher(input); 		
//    		matcher.matches();
    		
    		if(!matcher.matches()) {
    			throw new WrongFormatException("Zly format vstupu");
    			
    		}  
    		
    		else{
    			String a = matcher.group(1);
    			String row = matcher.group(2);
    			String column = matcher.group(3);
    			int value = (row.charAt(0)) -65;
    			if(value > field.getRowCount()) {
    				throw new WrongFormatException("Precerpanie indexu riadka");
    			}
    			int stlpec = Integer.parseInt(column);
    			if(stlpec > field.getColumnCount()) {
    				throw new WrongFormatException("Precerpane hodnoty stlpca");
    			}
    			if(a.equals("O")) {
    				field.openTile(value, stlpec);
    				return;
    			} else if(a.equals("M")) {
    				field.markTile(value, stlpec);
    				return;
    			}else if(a.equals("X")) {
    				System.exit(0);
    			}
    			
    		}
		}
}
