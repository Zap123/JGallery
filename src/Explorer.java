import java.io.File; 
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;

public class Explorer {
	private ArrayList<String> images;
	public String path;
	private File imgDir;
	
	public Explorer(String path){
		this.path = path;
		imgDir = new File(path);
		images = new ArrayList<String>();
		try {
			getImagesPaths();
		} catch (NotDirectoryException e) {
			e.printStackTrace();
		}
	}
	
	public void genThumnails(){
		
	}
	
	public void getImagesPaths() throws NotDirectoryException{
		if (imgDir.isDirectory()){
			for(File img : imgDir.listFiles(new ImagesFilter())){
				images.add(img.getName());
			}
		}
		else{
			throw(new NotDirectoryException(path));
		}
	}

	public ArrayList<String> getImages() {
		return images;
	}
}
