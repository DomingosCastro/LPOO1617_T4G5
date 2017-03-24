package dungeon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JButton;


public class createMap {

	private JFrame frame;
	JPanel matrix = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createMap window = new createMap();
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
	public createMap() {
		initialize();
		editMap();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 583, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		matrix.setBounds(259, 0, 298, 298);
		frame.getContentPane().add(matrix);
		
	}


	public void editMap() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}

//				JFrame frame = new JFrame("Edit Keep");
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.getContentPane().setLayout(new BorderLayout());
//				frame.add(new TestPane(10, 10));
//			    frame.pack();
//				frame.setLocationRelativeTo(null);
//				frame.setVisible(true);
				
				
				matrix.setLayout(new BorderLayout());
				matrix.add(new TestPane(10, 10));		
				matrix.revalidate();
				//matrix.setVisible(true);
				
			}
		});
	}

	public class TestPane extends JPanel {

		public TestPane(int i, int j) {
			setLayout(new GridBagLayout());

			GridBagConstraints gbc = new GridBagConstraints();

			for (int row = 0; row < i; row++) {
				for (int col = 0; col < j; col++) {
					gbc.gridx = col;
					gbc.gridy = row;

					CellPane cellPane = new CellPane();
					Border border = null;
					if (row < i-1) {
						if (col < j-1) {
							border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
						} else {
							border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
						}
					} else {
						if (col < j-1) {
							border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
						} else {
							border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
						}
					}
					cellPane.setBorder(border);
					add(cellPane, gbc);
				}
			}
		}
	}

	public class CellPane extends JPanel {

		private Color defaultBackground;

		public CellPane() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultBackground = getBackground();
					setBackground(Color.BLUE);				    
				}

				public void mousePressed(MouseEvent e) {
					setBackground(Color.RED);
				}

				public void mouseReleased(MouseEvent e) {
					setBackground(Color.BLACK);
					defaultBackground = getBackground();
					setBackground(defaultBackground);
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(defaultBackground);
				}
			});
			
			
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(50, 50);
		}
	}
}

