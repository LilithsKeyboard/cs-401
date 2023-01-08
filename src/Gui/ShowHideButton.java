package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Clustering.Main;

public class ShowHideButton extends JButton implements ActionListener {
	private ButtonMode mode;
	
	public ShowHideButton() {
		super();
		
		setMode(ButtonMode.HIDE);
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (mode) {
		case HIDE:
			Main.console.setVisible(false);
			setMode(ButtonMode.SHOW);
			break;
		case SHOW:
			Main.console.setVisible(true);
			setMode(ButtonMode.HIDE);
			break;
		}
		
		Main.console.revalidate();
		Main.console.repaint();
	}
	
	public ButtonMode getMode() {
		return mode;
	}
	
	public void setMode(ButtonMode mode) {
		this.mode = mode;
		
		switch (mode) {
		case HIDE:
			setText("Hide");
			break;
		case SHOW:
			setText("Show");
			break;
		}
		
		revalidate();
		repaint();
	}
}

enum ButtonMode {
	HIDE, SHOW
}