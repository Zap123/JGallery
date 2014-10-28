import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Template {
	ArrayList<String> paths;
	
	public Template(ArrayList<String> Images){
		this.paths = Images;
	}
	public void export(){
		try {
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		    		new FileOutputStream("gallery.html"), "utf-8"));
		    			for(String img: paths){
		    	//TODO: Temporary solution.
				writer.write("<img src='file://"+img+"' width='200px' height='200px'></img>");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
