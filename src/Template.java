import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Template {
	ArrayList<String> paths; 
	String HTMLImages = "";
	
	public Template(ArrayList<String> Images){
		this.paths = Images;
	}
	
	private void genImages(){
		for(String img: paths){
			HTMLImages += "<a target=blank href='file://"+img+"'"
					   + "<div class='imgdiv tilt'><img src='file://"+img+"'class='img'></img></div></a>";
		}
	}

	
	public void export(){
		try {
			genImages();
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		    						new FileOutputStream("gallery.html"), "utf-8"));
		    
			writer.write(new String(Files.readAllBytes(Paths.get("src/theme/basic.html"))));
		    writer.append(HTMLImages);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
