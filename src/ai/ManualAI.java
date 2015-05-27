package ai;

import java.util.*;
import gameEngine.*;
//work on
public class ManualAI implements AIX{
	
	private BoardState boardState;
	
	public ManualAI(BoardState latest){
		this.boardState = latest;
	}

	public int putCoin(){
		int column = 0;

		while(true){
			Scanner input = new Scanner(System.in);
			System.out.print("Which column would you like the PC to place the chip in (0-6)?");
			column = input.nextInt();

			if (boardState.isLegalMove(column)){
				input.close();
				break;
			}
			else{
				System.out.println("Invalid Column!");
			}
		}

		return column;
	}
}