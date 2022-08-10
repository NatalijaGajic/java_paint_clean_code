package files;

public class FileManager {

	private FileStrategy fileStrategy;
	
	public FileManager(FileStrategy fileStrategy) {
		this.fileStrategy = fileStrategy;
	}
	
	public void save(String filePath) {
		fileStrategy.saveFile(filePath);
		
	}

	public void open(String filePath) {
		fileStrategy.openFile(filePath);
	}

	public void setFileStrategy(FileStrategy fileStrategy) {
		this.fileStrategy = fileStrategy;
	}
	
	

}
