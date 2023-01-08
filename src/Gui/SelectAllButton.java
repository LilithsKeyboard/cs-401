package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectAllButton extends JButton implements ActionListener {
	private final FileSelectionPane pane;
	
	public SelectAllButton(FileSelectionPane pane) {
		super("Select All");
		
		this.pane = pane;
		
		setEnabled(false);
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (pane.getModel().getSize() > 0)
			pane.setSelectionInterval(0, pane.getModel().getSize() - 1);
	}
}
