public class JGallery {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out
                    .println("JGallery requires a dir of images as an input and return an html page");
        } else {
            Explorer dir = new Explorer(args[0]);
            Template T = new Template(dir.getImages(), dir.getThumbnails());
            T.export(dir.path);
            System.out.println("Open myphotogallery.html to see your gallery.");
        }
    }

}
