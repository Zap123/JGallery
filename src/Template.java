import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Template {
    ArrayList<String> image;
    ArrayList<String> thumb;
    String HTMLImages = "";

    public Template(ArrayList<String> Images, ArrayList<String> thumbnails) {
        this.image = Images;
        this.thumb = thumbnails;
    }

    private void genImages() {
        for (int i = 0; i < image.size(); i++) {
            HTMLImages += "<a target=blank href='" + image.get(i) + "'"
                    + "<div class='imgdiv tilt'><img src='" + thumb.get(i)
                    + "'class='img'></img></div></a>";
        }
    }

    public static String convertStreamToString(java.io.InputStream is) {
        try (java.util.Scanner s = new java.util.Scanner(is)) {
            return s.useDelimiter("\\A").hasNext() ? s.next() : "";
        }
    }

    public void export(String dir) {
        try {
            genImages();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(Paths.get(dir, "myphotogallery.html")
                            .toString()), "utf-8"));
            writer.write(convertStreamToString(getClass().getResourceAsStream(
                    "theme/basic.html")));
            writer.write(HTMLImages);
            writer.write(convertStreamToString(getClass().getResourceAsStream(
                    "theme/style.html")));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
