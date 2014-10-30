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

    public void export(String dir) {
        try {
            genImages();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(Paths.get(dir, "myphotogallery.html")
                            .toString()), "utf-8"));

            writer.write(new String(Files.readAllBytes(Paths
                    .get("src/theme/basic.html"))));
            writer.write(HTMLImages);
            writer.write(new String(Files.readAllBytes(Paths
                    .get("src/theme/style.html"))));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
