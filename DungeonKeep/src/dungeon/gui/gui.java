package dungeon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dungeon.logic.TileType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
public class gui extends JFrame implements KeyListener {

	private JFrame frame;
	private JTextField TextnumberOgres;
	static private GamePanel gamePanel;
	private JPanel menuWindow;
	private JButton quitButton;
	private JPanel gameWindow;
	private JPanel editWindow;
	private JPanel editPanel;
	private JPanel editDimensions;
	private JSlider linesSlider;
	private JSlider columnsSlider;
	private JLabel lblLines;
	private JLabel lblColumns;
	private JButton dimensionsBack;
	private JButton btnNext;
	private JButton btnDoor;
	private JButton btnKey;
	private JButton btnFloor;
	private JButton btnOgre;
	private JButton btnHero;
	private JButton btnPlay; 
	private JButton editBack;
	private static JLabel stateLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblEditKeepLevel;
	private JLabel 	lblGuardPersonality;
	private JLabel lblNumberOfOgres;
	private JPanel settingsWindow;
	private JButton btnNewButton_1;
	private JButton btnSaveEnemis;
	private JButton btnEdit; 
	private JButton newGameButton;
	private JButton btnSaveBoard;
	private JButton btnWall;
	private JComboBox<Object> comboBox; 
	int choseGuard=0;
	int numberOgres=1;
	private JLabel lblDungeonKeep;
	private JLabel label;
	private JLabel lblGameSettings;
	private JLabel lblCreateKeepLevel;
	private JLabel lblSetEnemys;
	//	private SaveLoad saveLoad = new SaveLoad();
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

	public void initialize() {
		frame = new JFrame(); frame.getContentPane().setLayout(new CardLayout(0, 0));

		initializeJPanels();
        initializeButtons();		
		initializeJSliders();	
		initializeTitleLabel();	
		settingLabels(); moreLabels();
		
		//		JButton btnNewButton = new JButton("Load Game");
		//		btnNewButton.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//				
		//				gamePanel=saveLoad.loadGamePanel();
		//				gamePanel.initializeGamePanel();
		//				gamePanel.setDungeon(saveLoad.loadDungeonKeep());
		//				
		//				menuWindow.setVisible(false);			
		//				gameWindow.setVisible(true);
		//
		//				gamePanel.repaint();
		//			}
		//		});
		//		btnNewButton.setFont(new Font("Centaur", Font.BOLD, 20));
		//		btnNewButton.setBounds(275, 291, 220, 49);
		//		menuWindow.add(btnNewButton);

	

		//		JButton saveGame = new JButton("Save Game");
		//		saveGame.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//				
		//				saveLoad.saveGamePanel(gamePanel);
		//				saveLoad.saveDungeonKeep(gamePanel.getDungeon());
		//				
		//			}
		//		});
		//		saveGame.setFont(new Font("Centaur", Font.BOLD, 15));
		//		saveGame.setBounds(635, 266, 103, 41);
		//		gameWindow.add(saveGame);

		TextnumberOgres = new JTextField();TextnumberOgres.setHorizontalAlignment(SwingConstants.CENTER);
		TextnumberOgres.setFont(new Font("Centaur", Font.PLAIN, 19));TextnumberOgres.setBounds(327, 217, 116, 25);
		settingsWindow.add(TextnumberOgres);TextnumberOgres.setColumns(10);
	
		comboBox.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {}});
		frame.setBounds(100, 100, 780, 524);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeButtons() {
		initializePlayButton(); initializeSttingsButton();
		initializeNewGameButton();initializeQuitButton();
		initializeSaveBoardButton();initializeBackEditButton();
		initializeWallButton();	initializExitDoor();
		initializeKeyButton();initializeFloorButton();
		initializeOgreButton();	initializeHeroButton();
		initializeBackButton();	initialieNextButton();		
		initializeSaveEnimiesButton();initializeEditButton();		
	}

	private void moreLabels() {stateLabel = new JLabel("");stateLabel.setBounds(169, 54, 256, 51);	gamePanel.add(stateLabel);
	stateLabel.setFont(new Font("Centaur", Font.BOLD, 21));	stateLabel.setHorizontalAlignment(SwingConstants.CENTER);

	label = new JLabel("Edit Keep Level");label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setFont(new Font("Centaur", Font.BOLD, 34));label.setBounds(10, 11, 704, 51);	editWindow.add(label);		

	lblLines = new JLabel("Lines");	lblLines.setHorizontalAlignment(SwingConstants.CENTER);
	lblLines.setFont(new Font("Centaur", Font.PLAIN, 20));lblLines.setBounds(314, 151, 98, 35);editDimensions.add(lblLines);

	lblColumns = new JLabel("Columns");	lblColumns.setHorizontalAlignment(SwingConstants.CENTER);
	lblColumns.setFont(new Font("Centaur", Font.PLAIN, 20));lblColumns.setBounds(314, 276, 98, 24);editDimensions.add(lblColumns);

	lblNewLabel_1 = new JLabel("Maze Dimensions");	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setFont(new Font("Centaur", Font.BOLD, 17));lblNewLabel_1.setBounds(283, 94, 149, 27);editDimensions.add(lblNewLabel_1);
		
	}

	private void settingLabels() {
		lblGameSettings = new JLabel("Game Settings");	lblGameSettings.setFont(new Font("Centaur", Font.BOLD, 30));
		lblGameSettings.setHorizontalAlignment(SwingConstants.CENTER);lblGameSettings.setBounds(220, 33, 321, 69);		settingsWindow.add(lblGameSettings);

		lblCreateKeepLevel = new JLabel("Create Keep Level");lblCreateKeepLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateKeepLevel.setFont(new Font("Centaur", Font.BOLD, 18));	lblCreateKeepLevel.setBounds(286, 320, 201, 31);		settingsWindow.add(lblCreateKeepLevel);

		lblSetEnemys = new JLabel("Set Enemies");lblSetEnemys.setFont(new Font("Centaur", Font.BOLD, 19));
		lblSetEnemys.setHorizontalAlignment(SwingConstants.CENTER);	lblSetEnemys.setBounds(306, 137, 143, 41);		settingsWindow.add(lblSetEnemys);

		lblEditKeepLevel = new JLabel("Edit Keep Level");lblEditKeepLevel.setFont(new Font("Centaur", Font.BOLD, 34));
		lblEditKeepLevel.setBounds(248, 11, 251, 51);editDimensions.add(lblEditKeepLevel);

		lblGuardPersonality = new JLabel("Guard Personality");	lblGuardPersonality.setFont(new Font("Centaur", Font.PLAIN, 16));
		lblGuardPersonality.setHorizontalAlignment(SwingConstants.CENTER);	lblGuardPersonality.setBounds(109, 189, 189, 31);settingsWindow.add(lblGuardPersonality);

		String[] names={"Rookie", "Drunken", "Suspicious"};	comboBox = new JComboBox<Object>(names);
		comboBox.setFont(new Font("Centaur", Font.BOLD, 16));	comboBox.setBounds(149, 215, 116, 25);	settingsWindow.add(comboBox);

		lblNumberOfOgres = new JLabel("Number of Ogres");lblNumberOfOgres.setFont(new Font("Centaur", Font.PLAIN, 16));
		lblNumberOfOgres.setBounds(329, 189, 156, 31);	settingsWindow.add(lblNumberOfOgres);
	}

	private void initializeSaveEnimiesButton() {
		btnSaveEnemis = new JButton("Save");btnSaveEnemis.setFont(new Font("Centaur", Font.BOLD, 16));
		btnSaveEnemis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {boolean valid=true;	try{				
				choseGuard=comboBox.getSelectedIndex();	numberOgres=Integer.parseInt(TextnumberOgres.getText());
				if(numberOgres<0){valid=false;JOptionPane.showMessageDialog(null, "invalid");}
			}catch (Exception e1){valid=false;JOptionPane.showMessageDialog(null, "invalid");}

			if(valid){menuWindow.setVisible(true);settingsWindow.setVisible(false);	gamePanel.setEditedBoardState(false);}}});
		btnSaveEnemis.setBounds(495, 215, 116, 26);	settingsWindow.add(btnSaveEnemis);
	}

	private void initializeJSliders() {
		linesSlider = new JSlider( 5, 30, 10);	linesSlider.setBounds(217, 182, 295, 51);editDimensions.add(linesSlider);
		linesSlider.setPaintTicks(true);linesSlider.setPaintLabels(true);linesSlider.setMajorTickSpacing(5);
		linesSlider.setMinorTickSpacing(5);

		columnsSlider = new JSlider( 5, 30, 10);columnsSlider.setBounds(217, 305, 295, 51);	editDimensions.add(columnsSlider);
		columnsSlider.setPaintTicks(true);columnsSlider.setPaintLabels(true);
		columnsSlider.setMajorTickSpacing(5);columnsSlider.setMinorTickSpacing(5);
	}

	private void initialieNextButton() {
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).initializeBoardEditing(linesSlider.getValue(), columnsSlider.getValue());
				gamePanel.clearNewPositions();

				editPanel.repaint();
				editPanel.setVisible(true);
				editDimensions.setVisible(false);
				editWindow.setVisible(true);

			}
		});
		btnNext.setBounds(377, 433, 89, 35);
		editDimensions.add(btnNext);
	}

	private void initializeBackButton() {
		dimensionsBack = new JButton("Back");
		dimensionsBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editDimensions.setVisible(false);
				settingsWindow.setVisible(true);
				((GamePanel) editPanel).setEditState(false);
			}
		});
		dimensionsBack.setBounds(258, 433, 89, 35);
		editDimensions.add(dimensionsBack);

	}


	private void initializeEditButton() {
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Centaur", Font.BOLD, 16));
		btnEdit.setBounds(329, 362, 114, 46);
		settingsWindow.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingsWindow.setVisible(false);
				editDimensions.setVisible(true);				
				gamePanel.setEditState(true);				
			}
		});


	}

	private void initializeHeroButton() {
		btnHero = new JButton("Hero");
		btnHero.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(TileType.HERO);
			}
		});
		btnHero.setBounds(667, 232, 78, 54);
		editWindow.add(btnHero);
	}

	private void initializeOgreButton() {
		btnOgre = new JButton("Ogre");
		btnOgre.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(TileType.OGRE);
			}
		});
		btnOgre.setBounds(667, 297, 78, 54);
		editWindow.add(btnOgre);
	}

	private void initializeFloorButton() {
		btnFloor = new JButton("Floor");
		btnFloor.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(TileType.FLOOR);
			}
		});
		btnFloor.setBounds(667, 362, 78, 54);
		editWindow.add(btnFloor);
	}

	private void initializeKeyButton() {
		btnKey = new JButton("Key");
		btnKey.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(TileType.KEY);
			}
		});
		btnKey.setBounds(667, 167, 78, 54);
		editWindow.add(btnKey);
	}

	private void initializExitDoor() {
		btnDoor = new JButton("Exit");
		btnDoor.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(TileType.DOOR);
			}
		});
		btnDoor.setBounds(667, 102, 78, 54);
		editWindow.add(btnDoor);
	}

	private void initializeWallButton() {
		btnWall = new JButton("Wall");
		btnWall.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((GamePanel) editPanel).setAddingTile(TileType.WALL);
			}
		});
		btnWall.setBounds(667, 40, 78, 54);
		editWindow.add(btnWall);
	}

	private void initializeBackEditButton() {
		editBack= new JButton("Back");
		editBack.setFont(new Font("Centaur", Font.PLAIN, 20));
		editBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				boolean save=false;

				editWindow.setVisible(false);
				editDimensions.setVisible(true);
				gamePanel.clearNewPositions();
			}
		});
		editBack.setBounds(255, 427, 102, 42);
		editWindow.add(editBack);

	}

	private void initializeSaveBoardButton(){
		btnSaveBoard = new JButton("Save");
		btnSaveBoard.setFont(new Font("Centaur", Font.PLAIN, 20));
		btnSaveBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean save=true;
				boolean valid=((GamePanel) editPanel).endEdition(save);

				if (valid){				
					editWindow.setVisible(false);
					menuWindow.setVisible(true);
				}
				else JOptionPane.showMessageDialog(frame, "Must have a Hero, a Key and an Exit Door!");
			}
		});
		btnSaveBoard.setBounds(399, 427, 102, 42);
		editWindow.add(btnSaveBoard);
	}
	private void initializeQuitButton(){
		quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Centaur", Font.BOLD, 15));
		quitButton.setBounds(634, 266, 104, 41);
		gameWindow.add(quitButton);

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setVisible(false);
				menuWindow.setVisible(true);
				gamePanel.endGame();
			}
		});
	}

	private void initializeNewGameButton() {
		newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Centaur", Font.BOLD, 15));
		newGameButton.setBounds(634, 177, 104, 41);
		gameWindow.add(newGameButton);
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gamePanel.startGame(choseGuard, numberOgres);
				gamePanel.repaint();

			}
		});
	}

	private void initializeJPanels() {
		menuWindow = new JPanel(); frame.getContentPane().add(menuWindow, "name_5231803590608");menuWindow.setLayout(null);		
		gameWindow = new JPanel();frame.getContentPane().add(gameWindow, "name_6288929371178");	gameWindow.setLayout(null);
		gamePanel= new GamePanel();	gamePanel.setBounds(28, -14, 570, 564);	gameWindow.add(gamePanel);gamePanel.setLayout(null);
		editWindow = new JPanel();frame.getContentPane().add(editWindow, "name_31523719216263");editWindow.setLayout(null);
		editPanel = new GamePanel();editPanel.setBounds(31, -34, 616, 461);	editWindow.add(editPanel);editPanel.setLayout(null);
		editDimensions = new JPanel();frame.getContentPane().add(editDimensions, "name_61773752494836");editDimensions.setLayout(null);
		settingsWindow = new JPanel();frame.getContentPane().add(settingsWindow, "name_198868520090610");settingsWindow.setLayout(null);
	}

	private void initializeTitleLabel() {
		lblDungeonKeep = new JLabel("Dungeon Keep ");
		lblDungeonKeep.setFont(new Font("Centaur", Font.BOLD, 40));
		lblDungeonKeep.setHorizontalAlignment(SwingConstants.CENTER);
		lblDungeonKeep.setBounds(158, 47, 465, 79);
		menuWindow.add(lblDungeonKeep);
	}

	private void initializeSttingsButton() {
		btnNewButton_1 = new JButton("Settings");
		btnNewButton_1.setFont(new Font("Centaur", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuWindow.setVisible(false);
				settingsWindow.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(274, 319, 221, 49);
		menuWindow.add(btnNewButton_1);
	}

	private void initializePlayButton() {
		btnPlay= new JButton("Play");
		btnPlay.setFont(new Font("Centaur", Font.BOLD, 20));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuWindow.setVisible(false);			
				gameWindow.setVisible(true);

				gamePanel.startGame(choseGuard, numberOgres);
				gamePanel.repaint();
			}
		});
		menuWindow.add(btnPlay);
		btnPlay.setBounds(274, 207, 221, 49);	
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("teste2");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("teste3");
	}

	public static JLabel getLabel() {
		// TODO Auto-generated method stub
		return stateLabel;
	}

	public static void setStateText(String text){
		stateLabel.setText(text);
	}

	public GamePanel getGamePanel(){
		return gamePanel;
	}
}