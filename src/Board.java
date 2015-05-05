import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Board extends JPanel implements ActionListener {
	
	/**
	 * ?????
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer[][] board;

	/**
	 * Constructor for a sudoku panel
	 */
	public Board() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        board = new Integer[9][9];
        generateBoard();
    }

	/**
	 * Method to generate a new board
	 * NOTE: this isn't a real/valid sudoku board.
	 */
    public void generateBoard() {
    	Random r = new Random();
    	// Generate a completed game
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boolean numberPlaced = false;
				while (numberPlaced == false) {
					int newNum = r.nextInt(9) + 1;
					// check to see if we can place this number.
					// check along the line
					// check up the column
					// check surroundings. 
					board[i][j] = newNum;
					numberPlaced = true;
				}
				
			}
		}
		// randomly remove certain pieces
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// one in every 4 pieces should be removed.
				if (r.nextInt(4) % 4 == 0) {
					board[i][j] = 0;
				}
			}
		}
	}

    /**
     * Set the size of the panel
     */
	public Dimension getPreferredSize() {
        return new Dimension(460,460);
    }

	/**
	 * Method to paint the board on the screen
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // convert the graphics component into graphics 2D
        Graphics2D g2 = (Graphics2D) g;
        
        // draw grid lines
        for (int i = 0; i < 10; i++) {
        	g2.setStroke(new BasicStroke(1));
        	if (i % 3 == 0) {
        		g2.setStroke(new BasicStroke(2));
        	}
            g2.drawLine(5 + i*50, 5, 5 + i*50, 455);
            g2.drawLine(5, 5 + i*50, 455, 5 + i*50);
        }
        
        for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] > 0)
					g.drawString(board[i][j].toString(),25 + i*50, 35 + j*50);
			}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// when an action happens regenerate the board
		generateBoard();
		// now repaint the board on the screen
		repaint();
	}

}
