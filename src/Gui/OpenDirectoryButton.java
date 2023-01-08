package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class OpenDirectoryButton extends JButton implements ActionListener {
	private final FileSelectionPane pane;
	
	public OpenDirectoryButton(FileSelectionPane pane) {
		super("Open Directory");
		
		this.pane = pane;
		
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileSelectionModal modal = new FileSelectionModal(FileSelectionMode.DIRECTORY);
		
		if (modal.showModal() == JFileChooser.APPROVE_OPTION) {
			System.out.println(modal.getSelectedFile().getAbsolutePath());
			pane.getFilesFromDirectory(modal.getSelectedFile().getAbsolutePath());
		}
	}

}
