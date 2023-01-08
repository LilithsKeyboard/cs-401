package Clustering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Gui.AggregateButton;
import Gui.FileSelectionPane;
import Gui.OpenDirectoryButton;
import Gui.ResultPanel;
import Gui.SaveButton;
import Gui.SelectAllButton;
import Gui.ShowHideButton;

public class Main {
	public static final JTextArea console = new JTextArea();

	public static void main(String[] args) {

		JFrame frame=new JFrame("Cluster App");
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelUp = new JPanel();
		panelUp.setLayout(new GridLayout(1, 3));
		
		JPanel panelFiles = new JPanel();
		panelFiles.setLayout(new BorderLayout());
		
		FileSelectionPane fileSelectionPane = new FileSelectionPane();
		
		JScrollPane scroll = new JScrollPane (fileSelectionPane, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelFiles.add(scroll, BorderLayout.CENTER);
		panelFiles.add(new OpenDirectoryButton(fileSelectionPane), BorderLayout.NORTH);
		SelectAllButton selectAll = new SelectAllButton(fileSelectionPane);
		fileSelectionPane.setSelectAllButton(selectAll);
		
		panelFiles.add(selectAll, BorderLayout.SOUTH);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		
		panelCenter.add(new JLabel(" "), BorderLayout.NORTH);
		AggregateButton aggregateButton = new AggregateButton(fileSelectionPane);
		panelCenter.add(aggregateButton);
		
		fileSelectionPane.setAggregateButton(aggregateButton);
		
		JPanel panelResult = new JPanel();
		
		panelResult.setLayout(new BorderLayout());
		ResultPanel resultPane = new ResultPanel();
		
		scroll = new JScrollPane (resultPane, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		aggregateButton.setResultPane(resultPane);
		
		panelResult.add(scroll);
		
		SaveButton saveButton = new SaveButton();
		aggregateButton.setSaveButton(saveButton);
		
		panelResult.add(saveButton, BorderLayout.NORTH);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BorderLayout());
		
		scorePanel.add(new JLabel("Score (Accuracy):"), BorderLayout.NORTH);
		JLabel scoreLabel = new JLabel(" ");
		
		aggregateButton.setScoreLabel(scoreLabel);
		
		scorePanel.add(scoreLabel, BorderLayout.SOUTH);
		
		panelResult.add(scorePanel, BorderLayout.SOUTH);
		
		panelUp.add(panelFiles);
		panelUp.add(panelCenter);
		panelUp.add(panelResult);
		
		JPanel panelDown = new JPanel();
		panelDown.setLayout(new BorderLayout());
		
		console.setEnabled(false);
		console.setRows(12);
		console.setBackground(Color.black);
		console.setForeground(Color.GREEN);
		
		scroll = new JScrollPane (console, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelDown.add(scroll);
		
		JPanel showHidePanel = new JPanel();
		showHidePanel.setLayout(new BorderLayout());
		showHidePanel.add(new ShowHideButton(), BorderLayout.EAST);
		
		panelDown.add(showHidePanel, BorderLayout.NORTH);
		
		panelDown.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		frame.setLayout(new BorderLayout());
		frame.add(panelUp, BorderLayout.NORTH);
		frame.add(panelDown, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	public static void print(String text) {
		console.setText(console.getText() + text);
	}

	public static void println(String text) {
		console.setText(console.getText() + text + "\n");
	}
	
	public static void println() {
		println("");
	}
	
	public static void printf(String text, Object ...objects) {
		console.setText(console.getText() + String.format(text, objects));
	}
}
