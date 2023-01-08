package Gui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ResultPanel extends JList<String> {
	private final DefaultListModel listModel = new DefaultListModel();
	
	private ArrayList<ArrayList<String>> finalClustering;
	
	public ResultPanel() {
		setModel(listModel);
	}

	public ArrayList<ArrayList<String>> getFinalClustering() {
		return finalClustering;
	}

	public void setFinalClustering(ArrayList<ArrayList<String>> finalClustering) {
		this.finalClustering = finalClustering;
		
		updateList();
	}
	
	private void updateList() {
		listModel.clear();
		
		for (ArrayList<String> cluster : finalClustering) {
			StringBuilder clusterText = new StringBuilder();
			int counter = 0;
			
			for (String item : cluster) {
				clusterText.append(item).append(' ');
			}
			
			listModel.add(counter++, clusterText.toString().strip());
		}
		
		revalidate();
		repaint();
	}
}
