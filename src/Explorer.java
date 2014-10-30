import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

import com.miginfocom.base64.Base64;

public class Explorer {
    private ArrayList<String> images;
    private ArrayList<String> thumbnails;
    public String path;
    private File imgDir;

    public Explorer(String path) {
        this.path = path;
        imgDir = new File(path);
        images = new ArrayList<String>();
        thumbnails = new ArrayList<String>();
        try {
            getImagesPaths();
        } catch (NotDirectoryException e) {
            e.printStackTrace();
        }
    }

    public void genThumbnails(File imgpath) {
        // Generate base64 encoded thumbnails
        BufferedImage img = null;
        try {
            img = ImageIO.read(imgpath);
        } catch (IOException e) {
        }
        BufferedImage scaledImage = Scalr.resize(img, 200);
        ByteArrayOutputStream thumbnail = new ByteArrayOutputStream();
        try {
            if (imgpath.getName().toLowerCase().endsWith(".gif"))
                ImageIO.write(scaledImage, "gif", thumbnail);
            else
                ImageIO.write(scaledImage, "jpg", thumbnail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = thumbnail.toByteArray();

        String base64bytes = Base64.encodeToString(bytes, false);
        String src = "data:image/png;base64," + base64bytes;
        thumbnails.add(src);
    }

    public void getImagesPaths() throws NotDirectoryException {
        if (imgDir.isDirectory()) {
            for (File img : imgDir.listFiles(new ImagesFilter())) {
                images.add(img.getName());
                genThumbnails(img);
            }
        } else {
            throw (new NotDirectoryException(path));
        }
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public ArrayList<String> getThumbnails() {
        return thumbnails;
    }
}
