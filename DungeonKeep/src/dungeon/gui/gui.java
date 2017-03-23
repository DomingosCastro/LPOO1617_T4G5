package dungeon.gui;

import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import dungeon.logic.DungeonKeep;
import dungeon.cli.ShowBoard;
//import dungeon.logic.Board;
//import dungeon.logic.Character;
//import dungeon.logic.Club;
//import dungeon.logic.Drunken;
//import dungeon.logic.Guard;
//import dungeon.logic.Hero;
//import dungeon.logic.Ogre;
//import dungeon.logic.Rookie;
//import dungeon.logic.Suspicious;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.ArrayList;
//import java.util.Scanner;
import java.awt.event.ActionEvent;
//import javax.swing.JTextArea;
import javax.swing.JComboBox;
//import javax.swing.JPanel;

public class gui extends JFrame implements KeyListener {

	private JFrame frame;
	private JTextField TextChoseGuard;
	private JTextField TextnumberOgres;
	private JButton up;
	private JButton rigth;
	private JButton down;
	private JButton left;
//	private DungeonKeep game ;
	private GamePanel gamePanel;
	ShowBoard showBoard = new ShowBoard();
	//String state;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
	        
		initialize();	
	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		
		gamePanel= new GamePanel();
	  //  gamePanel.startGame();
	    
		gamePanel.setBounds(258, 32, 477, 478);
//		frame.getContentPane().add(gamePanel);
		
		frame = new JFrame();
			frame.getContentPane().add(gamePanel);	
		frame.setBounds(100, 100, 833, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(20, 25, 127, 14);
		frame.getContentPane().add(lblGuardPersonality);

		String[] names={"Rookie", "Drunken", "Suspicious"};
		JComboBox comboBox = new JComboBox(names);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox.setBounds(130, 22, 86, 20);
		frame.getContentPane().add(comboBox);
		

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 56, 152, 14);
		frame.getContentPane().add(lblNumberOfOgres);

		TextnumberOgres = new JTextField();
		TextnumberOgres.setBounds(130, 53, 86, 20);
		frame.getContentPane().add(TextnumberOgres);
		TextnumberOgres.setColumns(10);

		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choseGuard=-1;
				int numberOgres=-1;
				
				try{					
					
					 choseGuard=comboBox.getSelectedIndex();
				     numberOgres=Integer.parseInt(TextnumberOgres.getText());
		
		
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null, "invalid");
				}
				
				gamePanel.startGame(choseGuard, numberOgres);
				gamePanel.repaint();
				
				frame.getContentPane().add(gamePanel);
				
				
				
			}
		});

		btnNewGame.setBounds(38, 99, 166, 37);
		frame.getContentPane().add(btnNewGame);

		up = new JButton("UP");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.playTurn('w');

			}
		});
		up.setBounds(90, 147, 74, 29);
		frame.getContentPane().add(up);

		rigth = new JButton("RIGTH");
		rigth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.playTurn('d');
//				if(state=="normal")
//				state=game.playTurn('d',textArea);	
			}
		});
		rigth.setBounds(142, 196, 74, 29);
		frame.getContentPane().add(rigth);

		down = new JButton("DOWN");
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gamePanel.playTurn('s');
				
//				if(state=="normal")
//				state=game.playTurn('s',textArea);	

			}
		});
		down.setBounds(90, 251, 74, 29);
		frame.getContentPane().add(down);

		left = new JButton("LEFT");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gamePanel.playTurn('a');
				
			}
		});
		left.setBounds(38, 196, 74, 29);
		frame.getContentPane().add(left);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.setGameState("quit");
			}
		});
		btnNewButton.setBounds(45, 304, 159, 29);
		frame.getContentPane().add(btnNewButton);
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}
	
	private void performKeyAction(int keyCode) {
		
		switch (keyCode) {
		case KeyEvent.VK_RIGHT:
				gamePanel.playTurn('d');
			break;
		case KeyEvent.VK_LEFT:	
				gamePanel.playTurn('a');
			break;
		case KeyEvent.VK_UP:
				gamePanel.playTurn('w');
			break;
		case KeyEvent.VK_DOWN:	
				gamePanel.playTurn('s');
			break;
		default:

			break;
		}
	}
}