package Gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class FileSelectionPane extends JList<String> {
	public static final String FILE_EXTENSION = ".rsf";
	
	private final ArrayList<String> files;
	
	private final DefaultListModel listModel = new DefaultListModel();
	
	private AggregateButton aggregateButton;
	
	private SelectAllButton selectAllButton;
	
	private String directory = "";
	
	public FileSelectionPane() {
		files = new ArrayList<>();
		setModel(listModel);
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	protected void getFilesFromDirectory(String path) {
		List<String> files = Stream.of(new File(path).listFiles())
				.filter(f -> f.isFile() && f.getAbsolutePath().endsWith(FILE_EXTENSION))
				.map(File::getName)
				.collect(Collectors.toList());
	
		this.directory = path;
		
		this.files.clear();
		this.files.addAll(files);
		
		updateList();
	}
	
	private void updateList() {
		listModel.clear();
		listModel.addAll(files);
		
		if (!files.isEmpty()) {
			aggregateButton.setEnabled(true);
			selectAllButton.setEnabled(true);
		} else {
			aggregateButton.setEnabled(false);
			selectAllButton.setEnabled(false);
		}
		
		this.revalidate();
		this.repaint();
	}

	public ArrayList<String> getFiles() {
		return files;
	}
	
	public List<String> getSelectedFiles() {
		ArrayList<String> selectedFiles = new ArrayList<>();
		
		for (int i : this.getSelectedIndices())
			selectedFiles.add(directory + "/" + files.get(i));
		
		return selectedFiles;
	}

	public void setAggregateButton(AggregateButton aggregateButton) {
		this.aggregateButton = aggregateButton;
	}

	public void setSelectAllButton(SelectAllButton selectAllButton) {
		this.selectAllButton = selectAllButton;
	}
}
