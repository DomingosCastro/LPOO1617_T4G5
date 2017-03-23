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
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
//import javax.swing.JPanel;

public class gui extends JFrame implements KeyListener {

	private JFrame frame;
	private JTextField TextChoseGuard;
	private JTextField TextnumberOgres;
	private JButton up;
	private JButton rigth;
	private JButton down;
	private JButton left;
	static private GamePanel gamePanel;
	ShowBoard showBoard = new ShowBoard();
	private JPanel menuWindow;
	private JButton quitButton;
	private JPanel gameWindow;
	private JTextField stateText;

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
		frame.getContentPane().setLayout(new CardLayout(0, 0));


		menuWindow = new JPanel();
		frame.getContentPane().add(menuWindow, "name_5231803590608");
		menuWindow.setLayout(null);

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuWindow.setVisible(false);			
				gameWindow.setVisible(true);

			}
		});
		btnPlay.setBounds(273, 220, 221, 49);
		menuWindow.add(btnPlay);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(273, 313, 221, 49);
		menuWindow.add(btnEdit);
		String[] names={"Rookie", "Drunken", "Suspicious"};

		gameWindow = new JPanel();
		frame.getContentPane().add(gameWindow, "name_6288929371178");
		gameWindow.setLayout(null);

		gamePanel= new GamePanel();
		gamePanel.setBounds(10, 66, 487, 447);
		gameWindow.add(gamePanel);
		gamePanel.setLayout(null);

		up = new JButton("UP");
		up.setBounds(589, 184, 65, 39);
		gameWindow.add(up);

		rigth = new JButton("RIGTH");
		rigth.setBounds(650, 233, 65, 39);
		gameWindow.add(rigth);

		down = new JButton("DOWN");
		down.setBounds(589, 282, 65, 39);
		gameWindow.add(down);

		left = new JButton("LEFT");
		left.setBounds(528, 233, 65, 39);
		gameWindow.add(left);

		quitButton = new JButton("Quit");
		quitButton.setBounds(528, 391, 187, 59);
		gameWindow.add(quitButton);
		
				stateText = new JTextField();
				stateText.setBounds(533, 78, 148, 27);
				gameWindow.add(stateText);
				stateText.setText(gamePanel.getStateText());
				stateText.setColumns(10);
				
						JLabel lblGuardPersonality = new JLabel("Guard Personality");
						lblGuardPersonality.setBounds(223, 14, 159, 14);
						gameWindow.add(lblGuardPersonality);
						JComboBox comboBox = new JComboBox(names);
						comboBox.setBounds(358, 11, 86, 20);
						gameWindow.add(comboBox);
						
						
								JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
								lblNumberOfOgres.setBounds(233, 39, 86, 14);
								gameWindow.add(lblNumberOfOgres);
								
								
										JButton newGameButton = new JButton("New Game");
										newGameButton.setBounds(511, 14, 192, 51);
										gameWindow.add(newGameButton);
										
												TextnumberOgres = new JTextField();
												TextnumberOgres.setBounds(358, 35, 86, 20);
												gameWindow.add(TextnumberOgres);
												TextnumberOgres.setColumns(10);
										newGameButton.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {

												
												int choseGuard=0;
												int numberOgres=1;
												boolean valid=true;
												
													try{				
														choseGuard=comboBox.getSelectedIndex();
														numberOgres=Integer.parseInt(TextnumberOgres.getText());

													}catch (Exception e1){
														valid=false;
														JOptionPane.showMessageDialog(null, "invalid");
													}
												
												if(valid){
												menuWindow.setVisible(false);
												gameWindow.setVisible(true);
												
												gamePanel.startGame(choseGuard, numberOgres);
												gamePanel.repaint();
												}
											}
										});
						
						
						
								comboBox.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
									}
								});
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setVisible(false);
				menuWindow.setVisible(true);
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gamePanel.playTurn('a');
				stateText.setText(gamePanel.getStateText());
			}
		});

		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gamePanel.playTurn('s');
				stateText.setText(gamePanel.getStateText());
			}
		});
		rigth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.playTurn('d');
				stateText.setText(gamePanel.getStateText());
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.playTurn('w');
				stateText.setText(gamePanel.getStateText());

			}
		});
		frame.setBounds(100, 100, 760, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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