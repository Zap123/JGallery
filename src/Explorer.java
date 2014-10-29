import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Explorer {
	private ArrayList<String> images;
	private String path;
	private File imgDir;
	
	public Explorer(String path){
		this.path = path;
		images = new ArrayList<String>();
		try {
			getImagesPaths();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getImagesPaths() throws FileNotFoundException{
		imgDir = new File(path);
		if (imgDir.exists()){
			for(File img : imgDir.listFiles(new ImagesFilter())){
				images.add(img.toString());
			}
		}
		else{
			throw(new FileNotFoundException());
		}
	}

	public ArrayList<String> getImages() {
		return images;
	}
}
