package Gui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class FileSelectionModal extends JFileChooser {
	private final FileSelectionMode mode;
	
	public FileSelectionModal(FileSelectionMode mode) {
		super(FileSystemView.getFileSystemView());
		
		this.mode = mode;
		
		switch (mode) {
		case DIRECTORY:
			setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			break;
		case SAVE:
			addChoosableFileFilter(new FileTypeFilter(FileSelectionPane.FILE_EXTENSION, "Relationship File"));
			setAcceptAllFileFilterUsed(false);
			break;
		}
	}
	
	public int showModal() {
		switch (mode) {
		case DIRECTORY:
			return showOpenDialog(null);
		case SAVE:
			return showSaveDialog(null);
		default:
			return JFileChooser.CANCEL_OPTION;
		}
	}

}

enum FileSelectionMode {
	DIRECTORY, SAVE
}
