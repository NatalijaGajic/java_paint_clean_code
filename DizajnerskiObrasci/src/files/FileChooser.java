package files;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser {

	private int userSelection;
	private File fileToSaveTo;
	private File fileToOpen;

	public FileChooser() {
	}

	public String showSaveToTxtFileDialog() {
		setDialogTitle("Select file to save to");
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		
		userSelection = showSaveDialog(null);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSaveTo = getSelectedFile();
			return fileToSaveTo.getAbsolutePath();
		}
		return null;
	}

	public String showOpenTxtFileDialog() {
		setDialogTitle("Select file to open");
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		
		userSelection = showOpenDialog(null);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToOpen = getSelectedFile();
			return fileToOpen.getAbsolutePath();
		}
		return null;
	}
}
