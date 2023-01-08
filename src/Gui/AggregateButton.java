package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import Clustering.Cluster;
import Clustering.DSM;
import Clustering.FileParser;
import Clustering.PivotAlgorithm;

public class AggregateButton extends JButton implements ActionListener {
	private final FileSelectionPane pane;
	
	private ResultPanel resultPane;
	
	private SaveButton saveButton;
	
	private JLabel scoreLabel;
	
	public AggregateButton(FileSelectionPane pane) {
		super("Aggregate");
		
		this.pane = pane;
		
		setEnabled(false);
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> files = (ArrayList<String>) pane.getSelectedFiles();
		
		FileParser.setSelectedFiles(files);
		
		Cluster cluster= FileParser.readAllClusters();
		cluster.printClusters();
		System.out.println();
		
		DSM DSM=new DSM();
		cluster.printDSMMatrix(DSM.ClusterAggregation(cluster));
		
		PivotAlgorithm pivot=new PivotAlgorithm();
		var result = pivot.Pivot(DSM.ClusterAggregation(cluster), cluster.getClusters().size()/2.0,cluster);
		
		resultPane.setFinalClustering(result);
		
		saveButton.setResult(result);
		saveButton.setCluster(cluster);
		saveButton.setEnabled(true);
		saveButton.revalidate();
		saveButton.repaint();
		
		scoreLabel.setText(String.format("%.2f", pivot.getAccuracy() * 100) + "% (" + pivot.getAccuracy() + ")");
		scoreLabel.validate();
		scoreLabel.repaint();
	}

	public void setResultPane(ResultPanel resultPane) {
		this.resultPane = resultPane;
	}

	public void setSaveButton(SaveButton saveButton) {
		this.saveButton = saveButton;
	}

	public void setScoreLabel(JLabel scoreLabel) {
		this.scoreLabel = scoreLabel;
	}

}
