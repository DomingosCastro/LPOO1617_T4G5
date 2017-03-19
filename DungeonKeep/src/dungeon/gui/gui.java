package dungeon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dungeon.cli.DungeonKeep;
import dungeon.cli.ShowBoard;
import dungeon.logic.Board;
import dungeon.logic.Character;
import dungeon.logic.Club;
import dungeon.logic.Drunken;
import dungeon.logic.Guard;
import dungeon.logic.Hero;
import dungeon.logic.Ogre;
import dungeon.logic.Rookie;
import dungeon.logic.Suspicious;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class gui {

	private JFrame frame;
	private JTextField TextChoseGuard;
	private JTextField TextnumberOgres;
	private JButton up;
	private JButton rigth;
	private JButton down;
	private JButton left;
	DungeonKeep game = null;
	ShowBoard showBoard = new ShowBoard();
	String state;
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
		frame = new JFrame();
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 81, 257, 178);
		frame.getContentPane().add(textArea);
				
		frame.setBounds(100, 100, 523, 355);
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

				game = new DungeonKeep();				
               
				
				try{					
					//int choseGuard=Integer.parseInt(TextChoseGuard.getText());
					int choseGuard=comboBox.getSelectedIndex();
					int numberOgres=Integer.parseInt(TextnumberOgres.getText());
					game.setEnemys(choseGuard, numberOgres);
					
				}catch (Exception e1){
					JOptionPane.showMessageDialog(null, "invalid");
				}
				state="normal";
				textArea.removeAll();			
				game.initializeLevel();
				game.printBoard(textArea);
			}
		});

		btnNewGame.setBounds(295, 21, 166, 37);
		frame.getContentPane().add(btnNewGame);

		up = new JButton("UP");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(state=="normal")
				state=game.playTurn('w',textArea);
			}
		});
		up.setBounds(346, 87, 74, 29);
		frame.getContentPane().add(up);

		rigth = new JButton("RIGTH");
		rigth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(state=="normal")
				state=game.playTurn('d',textArea);	
			}
		});
		rigth.setBounds(387, 128, 74, 29);
		frame.getContentPane().add(rigth);

		down = new JButton("DOWN");
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(state=="normal")
				state=game.playTurn('s',textArea);	

			}
		});
		down.setBounds(346, 168, 74, 29);
		frame.getContentPane().add(down);

		left = new JButton("LEFT");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(state=="normal")
				state=game.playTurn('a',textArea);	

			}
		});
		left.setBounds(301, 127, 74, 29);
		frame.getContentPane().add(left);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state="quit";
			}
		});
		btnNewButton.setBounds(302, 229, 159, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setBounds(20, 279, 257, 14);
		frame.getContentPane().add(label);
		
						
	}
}

