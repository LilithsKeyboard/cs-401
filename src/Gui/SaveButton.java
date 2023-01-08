package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import Clustering.Cluster;

public class SaveButton extends JButton implements ActionListener {
	private Cluster cluster;
	
	private ArrayList<ArrayList<String>> result;
	
	public SaveButton() {
		super("Save");
		
		setEnabled(false);
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FileSelectionModal modal = new FileSelectionModal(FileSelectionMode.SAVE);
		
		if (modal.showModal() == JFileChooser.APPROVE_OPTION) {
			String fullPath = modal.getSelectedFile().getAbsolutePath();
			if (!fullPath.endsWith(FileSelectionPane.FILE_EXTENSION))
				fullPath += FileSelectionPane.FILE_EXTENSION;
			
			cluster.writeToFile(result, fullPath);
		}
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public void setResult(ArrayList<ArrayList<String>> result) {
		this.result = result;
	}

}
