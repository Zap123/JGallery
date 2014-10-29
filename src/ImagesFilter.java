import java.io.File;
import java.io.FileFilter;


public class ImagesFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        final String[] extensions = new String[] {"jpg", "png", "gif"};
        for (String extension : extensions)
        {
          if (file.getName().toLowerCase().endsWith(extension))
          {
            return true;
          }
        }
        return false;
    }
}
