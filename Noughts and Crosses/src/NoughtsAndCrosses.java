//Mandeep Sandhu 1222078 - Worksheet 13 - object oriented programming - final.

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public final class NoughtsAndCrosses { 
	public static void main(String[]args){ //main method runs entire program by calling the menu method	
		System.out.println("building game board...");
		gui.menu(); //main method calls menu method from class gui to run the game
    }
}

final class gui { //class gui holds all graphical user interface object methods
	final static JButton[] grid = new JButton[9]; //create a array of buttons 
	final static JPanel panel = new JPanel();
	static String iconPlayer; //create variable to hold players and Ai icons
	static String iconAi; 
	final static String empty = ".";
	
	final static void menu(){ //method creates frame, panel and other gui objects
		final JFrame menu = new JFrame("Noughts & Crosses Main Menu:");
    	menu.setVisible(true);
    	menu.setSize(600,400);
    	menu.setResizable(false);
    	menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	final JPanel menup = new JPanel();
    	menu.setContentPane(menup);
    	menup.setLayout(new GridLayout(2,2,0,0));
    	menup.setBackground(Color.WHITE); //create the frame and panel for the main menu
        
    	final JButton play = new JButton("Play"); //create menu buttons for the menu frame
    	menup.add(play);
    	play.setFont(new Font("sansserif",Font.BOLD,100));
    	play.setForeground(Color.LIGHT_GRAY);
    	final JButton help = new JButton("Help"); 	
    	menup.add(help);
    	help.setFont(new Font("sansserif",Font.BOLD,100));
    	help.setForeground(Color.WHITE);
    	final JButton exit = new JButton("Exit");  	
    	menup.add(exit);
    	exit.setFont(new Font("sansserif",Font.BOLD,100));
    	exit.setForeground(Color.WHITE);
    	final JLabel dev = new JLabel("   1222078 Mandeep Sandhu"); 
    	menup.add(dev);
    	dev.setFont(new Font("sansserif",Font.BOLD,20));
    	dev.setForeground(Color.LIGHT_GRAY);
    	
    	play.setBackground(Color.WHITE); //set buttons to user friendly colours
    	play.setOpaque(true);
    	help.setBackground(Color.LIGHT_GRAY);
    	help.setOpaque(true);
    	exit.setBackground(Color.LIGHT_GRAY);
    	exit.setOpaque(true);
    	
    	exit.addActionListener(new ActionListener(){ //implement action listeners for each menu button
    		public void actionPerformed(ActionEvent e){
    			System.out.println("exiting program...");
    			System.exit(0); //exits the program
    		}
    	});
    	play.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			System.out.println("building game board..."); //launches the game gui
    			gui.board();
    			gui.grid();
    			Turns.reset();
    			Turns.turn(); //method allows player to choose who begins the game
    			gui.moves(); //method displays moves by player and Ai
    		}
    	});
    	help.addActionListener(new ActionListener(){ //help button launches a new window displaying game rules
    		public void actionPerformed(ActionEvent e){
    			System.out.println("building Help window...");
    			JFrame frameText = new JFrame("Help / Tutorial and Rules:");
    			frameText.setVisible(true);
    			frameText.setSize(400,600);
    			frameText.setResizable(false);
    			frameText.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    			JPanel panelText = new JPanel();
    			frameText.setContentPane(panelText);
    			JTextArea text = new JTextArea("Welcom to noughts and crosses! "
    					+ "\n\nTo play the game hit the play button, \nwhen prompted select"
    					+ "\nwhether you would like to start the game \nas crosses or whether the computer should."
    					+ "\nWhen the game has begun you must try and \nmatch your icon a nought or a cross"
    					+ "\nvertically, horizontally or diagonally, \nthe computer will attempt to do the same"
    					+ "\nwhile trying to block your connections, \nthe first to connect their icon in three squares"
    					+ "\nwins the game, if all squares have been \noccupied without winner, \nthen the game is declared a draw."
    					+ "\nYou cannot make a move in an invalid square, \ninvalid squares all squares already occupied by a "
    					+ "\nnought or cross icon."); //tutorial window help text
    			panelText.add(text);
    			text.setFont(new Font("sansserif",Font.PLAIN,16));
    			text.setEditable(false);
    			panelText.setBackground(Color.WHITE); //implement three action listeners for the main menu buttons
    		}
    	});
	}
	final private static void grid(){ //method create the frame for the game
		JFrame frame = new JFrame("Game Board:"); //create the game grid frame
		frame.setVisible(true);
		frame.setSize(400,400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(panel);
		panel.setLayout(new GridLayout(3,3,0,0)); //sets panel to a grid layout 3 squares by 3 squares grid
	}
	final private static void board(){ //method create the board for the game, implements array of buttons in a 3 by 3 grid format
		for(int i=0;i<9;i++){
    		grid[i] = new JButton(empty); //sets each button by default to empty variable
    		grid[i].setBackground(Color.WHITE);
    		grid[i].setOpaque(true);
    		grid[i].setForeground(Color.WHITE);
    		grid[i].setFont(new Font("sansserif",Font.BOLD,100));
    		panel.add(grid[i]); //set colour scheme and font size
    	}
	}
	final static void player_move(boolean move_made){ //method determines who has made a move and displays text in move history window
		if(move_made == true){
			moveText.append("\n\n**You have made a move"); //append text to existing text in text area
		}
		else if(move_made == false){
			moveText.append("\n\n**Computer has made a move");
		}
	}
	
	static JTextArea moveText = new JTextArea(); //create frame to display game move history
	final private static void moves(){ //method creates a move history frame to display messages of moves performed to aid the player
		JFrame moves = new JFrame("Move History:");
		moves.setVisible(true);
		moves.setSize(300,600);
		moves.setResizable(false);
		moves.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel textPanel = new JPanel();
		moves.setContentPane(textPanel);
		textPanel.setBackground(Color.WHITE);
		moveText.setFont(new Font("sansserif",Font.PLAIN,15));
		textPanel.add(moveText); //move history window

	}
}

final class Turns { //holds methods for game objects
	final static void turn(){ //method determines who begins the game by prompting the user with a message
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to begin the game as crosses?", "Starting game...", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION){
			gui.iconPlayer = "X"; //sets the game starter to crosses
			gui.iconAi = "O";
			Player.player();
			System.out.println("You are crosses");
		}
		else if(choice == JOptionPane.NO_OPTION){
			gui.iconPlayer = "O";
			gui.iconAi = "X";		
			Ai.ai();
			System.out.println("You are noughts"); 
		}
	}
	final static void next_turn(boolean next){ //method determines whose turn is next 
		if(next == true){ //if true runs player method, for user to make a move
			System.out.println("Players turn...");
			Player.player();
		}
		else if(next == false){ //runs Ai method for Ai to make a move
			System.out.println("Computers turn...");
			Ai.ai();	
		}
	}
	
	final static void reset(){ //method resets the game board to empty to allowe another game to start
		int i = 0;
		while(gui.grid[i].getText() != gui.empty){
			for(i =0;i<9;++i){
				gui.grid[i].setForeground(Color.WHITE);
				gui.grid[i].setText(gui.empty); //resets all squares to empty
			}
			gui.moveText.setText("");
			turn(); //calls the choose turn method after reseting the board
		}
	}
	final static void replay(){ //method allows another game to be played
		int reply = JOptionPane.showConfirmDialog(null, "Play another game?", "Play again?", JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
			reset(); //will reset game board if yes selected else end the game
		}
		else if(reply == JOptionPane.NO_OPTION){
			System.exit(0);
		}
	}
}

final class Rules { //holds methods for game rules
	static void check_win(){ //checks if the user or Ai has won, lost or drawn
		if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[1].getText() == gui.iconPlayer && gui.grid[2].getText() == gui.iconPlayer
				|| gui.grid[3].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.iconPlayer
				|| gui.grid[6].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.iconPlayer
				//checks horizontal grids
				|| gui.grid[0].getText() == gui.iconPlayer && gui.grid[3].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.iconPlayer
				|| gui.grid[1].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.iconPlayer
				|| gui.grid[2].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.iconPlayer
				//checks vertical grids
				|| gui.grid[0].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.iconPlayer
				|| gui.grid[2].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.iconPlayer){
			win(); //checks diagonal grids
		
		}
		else if(gui.grid[0].getText() == gui.iconAi && gui.grid[1].getText() == gui.iconAi && gui.grid[2].getText() == gui.iconAi
				|| gui.grid[3].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[5].getText() == gui.iconAi
				|| gui.grid[6].getText() == gui.iconAi && gui.grid[7].getText() == gui.iconAi && gui.grid[8].getText() == gui.iconAi
				//checks grids as above for Ai icons
				|| gui.grid[0].getText() == gui.iconAi && gui.grid[3].getText() == gui.iconAi && gui.grid[6].getText() == gui.iconAi
				|| gui.grid[1].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[7].getText() == gui.iconAi
				|| gui.grid[2].getText() == gui.iconAi && gui.grid[5].getText() == gui.iconAi && gui.grid[8].getText() == gui.iconAi
				
				|| gui.grid[0].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[8].getText() == gui.iconAi
				|| gui.grid[2].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[6].getText() == gui.iconAi){
			lose();
		}
		else if(gui.grid[0].getText() != gui.empty && 
				gui.grid[1].getText() != gui.empty &&
				gui.grid[2].getText() != gui.empty &&
				gui.grid[3].getText() != gui.empty &&
				gui.grid[4].getText() != gui.empty &&
				gui.grid[5].getText() != gui.empty &&
				gui.grid[6].getText() != gui.empty &&
				gui.grid[7].getText() != gui.empty &&
				gui.grid[8].getText() != gui.empty){ //once all squares are occupied and no winner detected, declare the game a draw
			draw(); //checks for a draw if all squares are no longer set to default with a win or lose not being declared
		}
	}
	private static void win(){ //win method
		gui.moveText.append("\n\n - YOU WIN! -");
		JOptionPane.showMessageDialog(null, "You have won the game!");
		System.out.println("Player has won the game");
		Turns.replay(); //asks if the user would like to play again
		
	}
	private static void draw(){ //method
		gui.moveText.append("\n\n - DRAW -");
		JOptionPane.showMessageDialog(null, "The game is a draw");
		System.out.println("The game is a draw");
		Turns.replay();
		
		
	}
	private static void lose(){ //lose method
		gui.moveText.append("\n\n - YOU LOSE -");
		JOptionPane.showMessageDialog(null, "You have lost the game");
		System.out.println("The computer has won the game");
		Turns.replay();
		
	}
}

class Player { //class holds player interaction method
	final static void player(){ 
    	for(int i=0;i<9;i++){
    		final int index = i;
    		gui.grid[index].addActionListener(new ActionListener(){ //implements action listener on all buttons using for loop
    			public void actionPerformed(ActionEvent e){
    				if(gui.grid[index].getText() == gui.iconPlayer || gui.grid[index].getText() == gui.iconAi) //check if square is occupied
					{
						//do nothing when square is occupied and user interacts with button
					}
    				else
    				{
    					gui.grid[index].setForeground(Color.BLACK);
    					gui.grid[index].setText(gui.iconPlayer); //sets users icon to the square
    					gui.player_move(true); //records message that user has made a move in move history window
						Rules.check_win(); //checks to see if user has won after this move
						Turns.next_turn(false); //sets next turn to false which calls Ai method
						System.out.println(gui.iconPlayer + " successfully set"); //console message displays if icon set successfully
    				}
    			}
    		});
    	}
    } 
}

final class Ai { //class holds Ai methods
	final static void ai(){ //Ai method runs AI win_attempt method
		win_move(); //the AI will attempt to first make a winning move, if it cannot will then attempt to block the players move, if it cannot will resort to a random move
}
	final private static void check_square(int button){ //method checks if square is occupied
		if(gui.grid[button].getText() == gui.iconPlayer || gui.grid[button].getText() == gui.iconAi){
			random_move(); // will run random_move again if square selected is occupied to generate another random number
			System.out.println("square occupied, searching for empty square...");	
		}
		else
		{
			    gui.grid[button].setForeground(Color.RED);
				gui.grid[button].setText(gui.iconAi); //sets icon to Ai icon if probability finds a square which is not occupied
				gui.player_move(false); //records move in move history window
				Rules.check_win(); //checks to see if Ai has won
				Turns.next_turn(true); //sets turn to player
				System.out.println(gui.iconAi + " successfully set");
		}
	}
	
	final private static void random_move(){ //probability method generates 9 random numbers for random move
	Random rand = new Random();
	int x = rand.nextInt(9); //9 possible numbers for 9 squares on the game grid
    
	switch(x){ //selects case based on number generated
	case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: //random move made when random number generated out of 9, each number corresponds to a square on the grid
		check_square(x); //assigns number to variable x and checks to see if square is occupied at x	
		break;
	}
}
	//method takes the square where a win will result and attempts to make a move into that square	
		final private static void win_probability(int square){ //AI will make a winning move
			check_square(square);
				
		}
		//method takes the square where a imminent win will result for the player and attempts to block the player by making a move into that square
		final private static void block_player(int square){ //AI will block the players winning move
			check_square(square);
		}
	
	final private static void win_move() { //AI attempts to win the game, first priority
		//statements which allow the AI to make a intelligent move to win the game
		
		//horizontal win attempts
		if(gui.grid[0].getText() == gui.iconAi && gui.grid[1].getText() == gui.iconAi && gui.grid[2].getText() == gui.empty){win_probability(2);}
		else if(gui.grid[3].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[5].getText() == gui.empty){win_probability(5);}
		else if(gui.grid[6].getText() == gui.iconAi && gui.grid[7].getText() == gui.iconAi && gui.grid[8].getText() == gui.empty){win_probability(8);}
		
		else if(gui.grid[2].getText() == gui.iconAi && gui.grid[1].getText() == gui.iconAi && gui.grid[0].getText() == gui.empty){win_probability(0);}
		else if(gui.grid[5].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[3].getText() == gui.empty){win_probability(3);}
		else if(gui.grid[8].getText() == gui.iconAi && gui.grid[7].getText() == gui.iconAi && gui.grid[6].getText() == gui.empty){win_probability(6);}
		
		else if(gui.grid[0].getText() == gui.iconAi && gui.grid[2].getText() == gui.iconAi && gui.grid[1].getText() == gui.empty){win_probability(1);}
		else if(gui.grid[3].getText() == gui.iconAi && gui.grid[5].getText() == gui.iconAi && gui.grid[4].getText() == gui.empty){win_probability(4);}
		else if(gui.grid[6].getText() == gui.iconAi && gui.grid[8].getText() == gui.iconAi && gui.grid[7].getText() == gui.empty){win_probability(7);}
		
		//vertical win attempts
		else if(gui.grid[0].getText() == gui.iconAi && gui.grid[3].getText() == gui.iconAi && gui.grid[6].getText() == gui.empty){win_probability(6);}
		else if(gui.grid[1].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[7].getText() == gui.empty){win_probability(7);}
		else if(gui.grid[2].getText() == gui.iconAi && gui.grid[5].getText() == gui.iconAi && gui.grid[8].getText() == gui.empty){win_probability(8);}
		
		else if(gui.grid[6].getText() == gui.iconAi && gui.grid[3].getText() == gui.iconAi && gui.grid[0].getText() == gui.empty){win_probability(0);}
		else if(gui.grid[7].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[1].getText() == gui.empty){win_probability(1);}
		else if(gui.grid[8].getText() == gui.iconAi && gui.grid[5].getText() == gui.iconAi && gui.grid[2].getText() == gui.empty){win_probability(2);}
		
		else if(gui.grid[0].getText() == gui.iconAi && gui.grid[6].getText() == gui.iconAi && gui.grid[3].getText() == gui.empty){win_probability(3);}
		else if(gui.grid[1].getText() == gui.iconAi && gui.grid[7].getText() == gui.iconAi && gui.grid[4].getText() == gui.empty){win_probability(4);}
		else if(gui.grid[2].getText() == gui.iconAi && gui.grid[8].getText() == gui.iconAi && gui.grid[5].getText() == gui.empty){win_probability(5);}
		
		//diagonal win attempts
		else if(gui.grid[0].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[8].getText() == gui.empty){win_probability(8);}
		else if(gui.grid[8].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[0].getText() == gui.empty){win_probability(0);}
		else if(gui.grid[0].getText() == gui.iconAi && gui.grid[8].getText() == gui.iconAi && gui.grid[4].getText() == gui.empty){win_probability(4);}
		 
		else if(gui.grid[2].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[6].getText() == gui.empty){win_probability(6);}
		else if(gui.grid[6].getText() == gui.iconAi && gui.grid[4].getText() == gui.iconAi && gui.grid[2].getText() == gui.empty){win_probability(2);}
		else if(gui.grid[2].getText() == gui.iconAi && gui.grid[6].getText() == gui.iconAi && gui.grid[4].getText() == gui.empty){win_probability(4);}
		
		else{
			block_move(); //second priority to check for a opportunity to block a winning move
		}	
	}
		
	final private static void block_move(){ //this method holds statements which allows the AI to block the player move which may result in the AI losing
		if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[1].getText() == gui.iconPlayer && gui.grid[2].getText() == gui.empty){block_player(2);}
		else if(gui.grid[3].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.empty){block_player(5);}
		else if(gui.grid[6].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.empty){block_player(8);}
		
		else if(gui.grid[2].getText() == gui.iconPlayer && gui.grid[1].getText() == gui.iconPlayer && gui.grid[0].getText() == gui.empty){block_player(0);}
		else if(gui.grid[5].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[3].getText() == gui.empty){block_player(3);}
		else if(gui.grid[8].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.empty){block_player(6);}
		
		else if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[2].getText() == gui.iconPlayer && gui.grid[1].getText() == gui.empty){block_player(1);}
		else if(gui.grid[3].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.empty){block_player(4);}
		else if(gui.grid[6].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.empty){block_player(7);}
		
		//vertical win attempts
		else if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[3].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.empty){block_player(6);}
		else if(gui.grid[1].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.empty){block_player(7);}
		else if(gui.grid[2].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.empty){block_player(8);}
		
		else if(gui.grid[6].getText() == gui.iconPlayer && gui.grid[3].getText() == gui.iconPlayer && gui.grid[0].getText() == gui.empty){block_player(0);}
		else if(gui.grid[7].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[1].getText() == gui.empty){block_player(1);}
		else if(gui.grid[8].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.iconPlayer && gui.grid[2].getText() == gui.empty){block_player(2);}
		
		else if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.iconPlayer && gui.grid[3].getText() == gui.empty){block_player(3);}
		else if(gui.grid[1].getText() == gui.iconPlayer && gui.grid[7].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.empty){block_player(4);}
		else if(gui.grid[2].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.iconPlayer && gui.grid[5].getText() == gui.empty){block_player(5);}
		
		//diagonal win attempts
		else if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.empty){block_player(8);}
		else if(gui.grid[8].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[0].getText() == gui.empty){block_player(0);}
		else if(gui.grid[0].getText() == gui.iconPlayer && gui.grid[8].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.empty){block_player(4);}
		
		else if(gui.grid[2].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.empty){block_player(6);}
		else if(gui.grid[6].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.iconPlayer && gui.grid[2].getText() == gui.empty){block_player(2);}
		else if(gui.grid[2].getText() == gui.iconPlayer && gui.grid[6].getText() == gui.iconPlayer && gui.grid[4].getText() == gui.empty){block_player(4);}
		
		else{
			random_move(); //last priority to make a random move
		}
	
	}
	
}


