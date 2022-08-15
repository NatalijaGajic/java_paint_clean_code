package files;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;
	private int userSelection;
	private File fileToSaveTo;
	private File fileToOpen;

	public FileChooser() {
	}

	public String showSaveToTxtFileDialog() {
		
		setSaveToTxtFileDialogProperties();
		userSelection = showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSaveTo = getSelectedFile();
			return fileToSaveTo.getAbsolutePath();
		}
		return "";
	}
	
	private void setSaveToTxtFileDialogProperties() {
		setDialogTitle("Select file to save to");
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
	}

	public String showOpenTxtFileDialog() {
		setOpenTxtFileDialogProperties();
		userSelection = showOpenDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToOpen = getSelectedFile();
			return fileToOpen.getAbsolutePath();
		}
		return "";
	}
	
	private void setOpenTxtFileDialogProperties() {
		setDialogTitle("Select file to open");
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
	}
}
