import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
//import java.util.*;

import javax.swing.*;


public class Board extends JPanel implements ActionListener {
	
	public static final int CELL_HEIGHT = 60;
	public static final int CELL_WIDTH = 60;
	
	private static int SCREEN_HEIGHT;
	private static int SCREEN_WIDTH;


	private static final long serialVersionUID = 1L;
	
	private int[][] board;

	private int boardColumn;
	private int boardRow;
	
	private int currentPlayer;
	private int numPlayers;

	private boolean gameOver;
	
	//initialize default board
	public Board() {
		initBoard(7,6);
		this.numPlayers = 2;
		this.currentPlayer = 1;
    }

	//initialize custom board
	public Board(int column, int row) {
		initBoard(column,row);
		this.numPlayers = 2;
		this.currentPlayer = 1;
    }
	
	//reset game
	private void resetGame() {
		System.out.println("new game");
		this.initBoard(this.boardColumn, this.boardRow);
		currentPlayer = 1;
	}
	
	//initialize board game
	private void initBoard(int column, int row){
		this.boardColumn = column;
		this.boardRow = row;
		
		gameOver = false;
		
		SCREEN_HEIGHT = CELL_HEIGHT * this.boardRow + 10;
		SCREEN_WIDTH = CELL_WIDTH * this.boardColumn + 10;
		
		board = new int[this.boardRow][this.boardColumn];
		
        setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	//set windows size
	public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT);
    }

	//
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        //convert the graphics component into graphics 2D
        Graphics2D g2 = (Graphics2D) g;        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int cellWidth = (SCREEN_WIDTH-10) / this.boardColumn;
        int cellHeight = (SCREEN_HEIGHT-10) / this.boardRow;
        
        // draw row lines
        for (int row = 0; row <= this.boardRow; row++) {	
        	g2.setStroke(new BasicStroke(1));
        	g2.drawLine(5, 5 + row*cellHeight, SCREEN_WIDTH - 5, 5 + row*cellHeight);
        	//System.out.println("printing out " + (5 + row*cellHeight));
        }
        
        // draw column lines
        for (int col = 0; col <= this.boardColumn; col++) {
        	g2.setStroke(new BasicStroke(1));
        	g2.drawLine(5 + col*cellWidth, 5, 5 + col*cellWidth, SCREEN_HEIGHT - 5);
        	//System.out.println("printing out " + (5 + col*cellWidth));
        }
        
        //draw the circle
        for (int r = 0; r < this.boardRow; r++) {
			for (int c = 0; c < this.boardColumn; c++) {
				if (board[r][c] > 0)
				{
			        Ellipse2D.Double circle = new Ellipse2D.Double(xcoordinateCircle(c), SCREEN_HEIGHT - ycoordinateCircle(r) - 30, 30, 30);
			        
			        if (board[r][c] == 2) g2.setColor(Color.red);
			        if (board[r][c] == 1) g2.setColor(Color.yellow);
			        g2.fill(circle);			        
				}
			}
        }
        
        if(gameOver)
        {
        	g2.setColor(Color.blue);
        	Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 24);
        	g2.setFont(myFont);
        	
        	g2.drawString("GAME OVER PAL", 100, 100);
        	
        	
        	myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 16);
        	g2.setFont(myFont);
        	
        	if(isVictorious(1))
        	{
        		g2.drawString("Yellow Wins, BL m8 Red Coin", 50, 300);
        	}
        	else
        	{
        		g2.drawString("Red Wins, Yellow got rekt", 50, 300);
        	}
        	
        }
        
    }
    
    //get the center x-coordinate of the cell
    private int xcoordinateCircle(int c){
    	return ((c*CELL_WIDTH - 5) + ((c+1)*(CELL_WIDTH - 5)))/2;    	
    }
    
    //get the center y-coordinate of the cell
    private int ycoordinateCircle(int r){    	
    	return ((r*CELL_HEIGHT - 5) + ((r+1)*(CELL_HEIGHT - 5)))/2;    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// reset the game
		resetGame();
		// now repaint the board on the screen
		repaint();
	}
	
	//put coin into the board
	public void putCoin(int column)
	{
		if(gameOver) return;
		System.out.println(gameOver);
			for(int row = 0; row < this.boardRow; row++)
			{
				if(board[row][column] == 0)
				{
					board[row][column] = currentPlayer;
					//System.out.println("Put coin in " + String.valueOf(row) + "," + String.valueOf(column) + " by " + this.currentPlayer);
					if (this.isVictorious(this.currentPlayer)) {
						System.out.println(this.currentPlayer + " won!!!!");
						gameOver = true;
						//resetGame();
					}
					
					if (currentPlayer == numPlayers) {
						currentPlayer = 1;
					} else {
						currentPlayer++;
					}
	
					return;
				}
			}
		
		//System.out.println("Couldn't do it m8");
	}

	//check if a player wins
	public boolean isVictorious(int currentPlayer) {
		int sum = 0;
		
		//checks vertical
		for (int c = 0; c < this.boardColumn; c++) {
			for (int r = 0; r < this.boardRow; r++) {
				if (board[r][c] == currentPlayer) {
					sum++;
					if (sum == 4) return true;
				}
				else {
					sum = 0;
				}
			}			
		}
		
		//checks horizontal
		sum = 0;
		for (int r = 0; r < this.boardRow; r++) {
			for (int c = 0; c < this.boardColumn; c++) {
				if (board[r][c] == currentPlayer) {
					sum++;
					if (sum == 4) return true;
				}
				else {
					sum = 0;
				}
			}			
		}
		
		//checks diagonal left
		sum = 0;
		for (int c = -this.boardRow; c < this.boardColumn; c++) {
			for (int r = 0; r < this.boardRow; r++) {
				
				if(getCoin(r, c+r) == currentPlayer)
				{
					sum++;
					if(sum == 4) return true;
				}
				else
				{
					sum = 0;
				}
			}			
		}
		
		//checks diagonal right
		sum = 0;
		for (int c = 0; c < this.boardColumn + this.boardRow; c++) {
			for (int r = 0; r < this.boardRow; r++) {
				
				if(getCoin(r, c-r) == currentPlayer)
				{
					sum++;
					if(sum == 4) return true;
				}
				else
				{
					sum = 0;
				}
			}			
		}
				
		return false;
	}
	
	//gets the coin in a given row and column
	public int getCoin(int r, int c)
	{
		if(r < 0 || c < 0 || r >= this.boardRow || c >= this.boardColumn) return 0;
		
		return board[r][c];
	}
}
