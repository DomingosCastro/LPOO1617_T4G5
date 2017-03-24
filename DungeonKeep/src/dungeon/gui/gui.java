package dungeon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dungeon.cli.ShowBoard;
import dungeon.logic.TileType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JSlider;


public class gui extends JFrame implements KeyListener {

	private JFrame frame;
	private JTextField TextChoseGuard;
	private JTextField TextnumberOgres;
	static private GamePanel gamePanel;
	ShowBoard showBoard = new ShowBoard();
	private JPanel menuWindow;
	private JButton quitButton;
	private JPanel gameWindow;
	private JPanel doorButton;
	private JPanel editPanel;
	private JPanel editDimensions;
	private JSlider linesSlider;
	private JSlider columnsSlider;
	private JLabel lblLines;
	private JLabel lblColumns;
	private JButton dimensionsBack;
	private JButton btnNext;
	private ChangeListener listener;
	private JTextField textField;
	private TileType tile;
	private JButton btnDoor;
	private JButton button;
	private JButton btnKey;
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
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuWindow.setVisible(false);
				editDimensions.setVisible(true);				
				gamePanel.setEditState(true);				
			}
		});
		btnEdit.setBounds(273, 313, 221, 49);
		menuWindow.add(btnEdit);
		String[] names={"Rookie", "Drunken", "Suspicious"};

		gameWindow = new JPanel();
		frame.getContentPane().add(gameWindow, "name_6288929371178");
		gameWindow.setLayout(null);
		//	gameStateLabel.setText(gamePanel.getStateText());

		gamePanel= new GamePanel();
		gamePanel.setBounds(21, 60, 549, 488);
		gameWindow.add(gamePanel);
		gamePanel.setLayout(null);

		quitButton = new JButton("Quit");
		quitButton.setBounds(595, 312, 118, 41);
		gameWindow.add(quitButton);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(595, 120, 118, 14);
		gameWindow.add(lblGuardPersonality);
		JComboBox comboBox = new JComboBox(names);
		comboBox.setBounds(595, 139, 118, 20);
		gameWindow.add(comboBox);


		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(595, 170, 118, 14);
		gameWindow.add(lblNumberOfOgres);


		JButton newGameButton = new JButton("New Game");
		newGameButton.setBounds(595, 215, 118, 41);
		gameWindow.add(newGameButton);

		TextnumberOgres = new JTextField();
		TextnumberOgres.setBounds(595, 184, 118, 20);
		gameWindow.add(TextnumberOgres);
		TextnumberOgres.setColumns(10);

		doorButton = new JPanel();
		frame.getContentPane().add(doorButton, "name_31523719216263");
		doorButton.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean save=true;
				((GamePanel) editPanel).endEdition(save);
				doorButton.setVisible(false);
				menuWindow.setVisible(true);
			}
		});
		btnSave.setBounds(404, 483, 102, 42);
		doorButton.add(btnSave);

		JButton editBack = new JButton("Back");
		editBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean save=false;

				doorButton.setVisible(false);
				editDimensions.setVisible(true);

			}
		});
		editBack.setBounds(265, 483, 102, 42);
		doorButton.add(editBack);

		editPanel = new GamePanel();
		editPanel.setBounds(59, 21, 532, 438);
		doorButton.add(editPanel);
		editPanel.setLayout(null);
		
		JButton btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((GamePanel) editPanel).setAddingTile(tile.WALL);
			}
		});
		btnWall.setBounds(629, 85, 74, 65);
		doorButton.add(btnWall);
		
		btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(tile.DOOR);
			}
		});
		btnDoor.setBounds(629, 184, 74, 65);
		doorButton.add(btnDoor);
		
		button = new JButton("Door");
		button.setBounds(629, 184, 74, 65);
		doorButton.add(button);
		
		btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).setAddingTile(tile.KEY);
			}
		});
		btnKey.setBounds(629, 279, 74, 65);
		doorButton.add(btnKey);

		editDimensions = new JPanel();
		frame.getContentPane().add(editDimensions, "name_61773752494836");
		editDimensions.setLayout(null);

		 listener = new ChangeListener()
         {
            public void stateChanged(ChangeEvent event)
            {
               // update text field when the slider value changes
               JSlider source = (JSlider) event.getSource();
               textField.setText("" + source.getValue());
            }

		
         };
		
		linesSlider = new JSlider( 5, 30, 10);
		linesSlider.setBounds(250, 187, 206, 55);
		editDimensions.add(linesSlider);
		linesSlider.setPaintTicks(true);
		linesSlider.setPaintLabels(true);
		linesSlider.setMajorTickSpacing(5);
		linesSlider.setMinorTickSpacing(5);
	
        

		columnsSlider = new JSlider( 5, 30, 10);
		columnsSlider.setBounds(250, 293, 206, 48);
		editDimensions.add(columnsSlider);
		columnsSlider.setPaintTicks(true);
		columnsSlider.setPaintLabels(true);
		columnsSlider.setMajorTickSpacing(5);
		columnsSlider.setMinorTickSpacing(5);


		lblLines = new JLabel("Lines");
		lblLines.setBounds(162, 198, 46, 14);
		editDimensions.add(lblLines);

		lblColumns = new JLabel("Columns");
		lblColumns.setBounds(162, 293, 67, 14);
		editDimensions.add(lblColumns);

		dimensionsBack = new JButton("Back");
		dimensionsBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editDimensions.setVisible(false);
				menuWindow.setVisible(true);
			    ((GamePanel) editPanel).setEditState(false);
			}
		});
		dimensionsBack.setBounds(184, 479, 89, 23);
		editDimensions.add(dimensionsBack);

		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GamePanel) editPanel).initializeBoardEditing(linesSlider.getValue(), columnsSlider.getValue());


				editPanel.repaint();
				editPanel.setVisible(true);
				editDimensions.setVisible(false);
				doorButton.setVisible(true);

			}
		});
		btnNext.setBounds(404, 479, 89, 23);
		editDimensions.add(btnNext);



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
				gamePanel.endGame();
			}
		});

		frame.setBounds(100, 100, 760, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}