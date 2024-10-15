package dark_horizon;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import dark_horizon.Game.Choice;

public class UI {
	
	JFrame window; //Create a frame called window that will show up on screen
	Container gameContainer; //Create a container for the frame
	JPanel titlePanel, startButtonPanel, mainPanel, choicesPanel; //Panel like div for html
	JLabel titleName; //Label for title like a header
	Font titleFont = new Font ("Baskerville Old Face", Font.PLAIN, 100); //Font for title screen
	Font textFont = new Font ("Times New Roman", Font.PLAIN, 33); //Font for text
	Font buttonFont = new Font ("Times New Roman", Font.PLAIN, 35); //Font for Button
	JButton startButton, exitButton, choice1Button, choice2Button, choice3Button; //Buttons
	JTextArea mainTextArea; //Displays Text
	JProgressBar healthProgressBar; //This is a progress bar that shows the players health
	
	public void Game_UI(Choice choice) {
		//Create WIndow
		JFrame window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setTitle("Text Adventure");
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		gameContainer = window.getContentPane();
		
		//Create Title
		titlePanel = new JPanel();
		titlePanel.setBounds(94, 120, 600, 150);
		titlePanel.setOpaque(false);
		titleName = new JLabel("Dark Horizon");
		titleName.setForeground(Color.white);
		titleName.setFont(titleFont);
		
		//Create start and exit buttons
		startButtonPanel = new JPanel(new GridLayout(2, 1));
		startButtonPanel.setBounds(295, 380, 200, 100);
		startButtonPanel.setOpaque(false);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(buttonFont);
		startButton.setBorder(null);
		startButton.setFocusPainted(false);
		startButton.addActionListener(choice);
		startButton.setActionCommand("Start");
		
		exitButton = new JButton("EXIT");
		exitButton.setBackground(Color.black);
		exitButton.setForeground(Color.white);
		exitButton.setFont(buttonFont);
		exitButton.setBorder(null);
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(choice);
		exitButton.setActionCommand("Exit");
		
		//Create main panel for text
		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		
		//Create text area for text
		mainTextArea = new JTextArea();
		mainTextArea.setOpaque(false);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(textFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setHighlighter(null);
		mainTextArea.setEditable(false);
		mainTextArea.setFocusable(false);
		
		//Create choice panel for buttons
		choicesPanel = new JPanel(new GridLayout(3, 1));
		choicesPanel.setBounds(295, 350, 200, 200);
		choicesPanel.setOpaque(false);
		choicesPanel.setFocusable(false);
		
		//Create 3 buttons
		choice1Button = new JButton();
		choice1Button.setBackground(Color.black);
		choice1Button.setForeground(Color.white);
		choice1Button.setFont(buttonFont);
		choice1Button.setBorder(null);
		choice1Button.setFocusPainted(false);
		
		choice2Button = new JButton();
		choice2Button.setBackground(Color.black);
		choice2Button.setForeground(Color.white);
		choice2Button.setFont(buttonFont);
		choice2Button.setBorder(null);
		choice2Button.setFocusPainted(false);
		
		choice3Button = new JButton();
		choice3Button.setBackground(Color.black);
		choice3Button.setForeground(Color.white);
		choice3Button.setFont(buttonFont);
		choice3Button.setBorder(null);
		choice3Button.setFocusPainted(false);
		
		//Creates the progress bar
		healthProgressBar = new JProgressBar(); 
		healthProgressBar.setBorderPainted(false);
		healthProgressBar.setForeground(Color.green);
		healthProgressBar.setBackground(Color.red);
		healthProgressBar.setBorder(null);
		
		//Adds all of them
		mainPanel.add(mainTextArea);
		choicesPanel.add(choice1Button);
		choicesPanel.add(choice2Button);
		choicesPanel.add(choice3Button);		
		titlePanel.add(titleName);
		startButtonPanel.add(startButton);
		startButtonPanel.add(exitButton);
		
		gameContainer.add(healthProgressBar);
		gameContainer.add(titlePanel);
		gameContainer.add(startButtonPanel);
		gameContainer.add(mainPanel);
		gameContainer.add(choicesPanel);
		
		window.setVisible(true);	
	} //Creates the gamescreen for all of the text and edits of text
}
