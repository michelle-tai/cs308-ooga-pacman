public class PacManImageChange{

    private Button changeImage(){
        Button changePacManImage = new Button("Pac-Man Image");
        changePacManImage.setOnAction(choosePacMan(PacManView.getImage()));
    }

    public void choosePacMan(File imageFile) {
        ImageView pacmanImage = new ImageView();
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pacmanImage.setImage(image);
      } catch (IllegalArgumentException e){
            return;
        } catch (IOException e) {
            retryLoadFile(e.getMessage());
        }
    }

    private void retryLoadFile(String message) {
        boolean badFile;
        displayError(message);
        do {
            badFile = false;
            try {
                choosePacMan(getPacManImage(new Stage()));
            } catch (NullPointerException e){
                return;
            } catch (Exception e){
                displayError(e.getMessage());
                badFile = true;
            }
        } while (badFile);


        private File getPacManImage(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Pac-Man Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File(System.getProperty(XML_FILEPATH)));
        return fileChooser.showOpenDialog(stage);
    }
}