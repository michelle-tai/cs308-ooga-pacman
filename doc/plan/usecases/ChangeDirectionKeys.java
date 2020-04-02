public class ChangeDirectionKeys{

    public static final int SIZE_WIDTH = 500;
    public static final int SIZE_HEIGHT = 500;
    private static final String TITLE = "Settings";
    private static final Paint BACKGROUND = Color.AZURE;

    public ChangeDirectionKeys(){
        Stage stage = new Stage();
        stage.setScene(setScene());
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene setScene(){
        Group root = new Group();
        root.getChildren().addAll(createTextFields());
        return new Scene(root, SIZE_WIDTH, SIZE_HEIGHT, BACKGROUND);
    }

    private VBox createTextFields(){
        TextField rightKey = new TextField();
        rightKey.setPromptText("Right Key");
        TextField leftKey = new TextField();
        rightKey.setPromptText("Left Key");
        TextField upKey = new TextField();
        rightKey.setPromptText("Up Key");
        TextField downKey = new TextField();
        rightKey.setPromptText("Down Key");
        Button submitButton = new Button("Submit Keys");
        submitButton.setOnAction(submitKeys(rightKey.getText(), leftKey.getText(), upKey.getText(), downKey.getText()));
        VBox vbox = new VBox(rightKey, leftKey, upKey, downKey, submitButton);
        return vbox;
    }

    private void submitKeys(String rightKey, String leftKey, String upKey, String downKey){
        engine.changeKeys(KeyCode.rightKey, KeyCode.leftKey, KeyCode.upKey, KeyCode.downKey);
    }
    }

}