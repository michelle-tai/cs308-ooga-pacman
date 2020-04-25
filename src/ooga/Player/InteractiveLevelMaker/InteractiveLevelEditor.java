package ooga.Player.InteractiveLevelMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import ooga.Player.StartScreen;
import ooga.engine.GameException;

public class InteractiveLevelEditor {
  private ComboBox gameImages = new ComboBox();

  private static final int PADDING = 10;

  private HBox topbar;
  private BorderPane bp;
  private VBox completeGrid;


  private static final int BUTTON_HEIGHT = 100;
  private static final int BUTTON_WIDTH = 150;
  private static final int PANE_HEIGHT = 800;
  private static final int PANE_WIDTH = 1400;
  private static final int IV_LENGTH = 25;
  private static final int START_VALUE = 5;



  private ResourceBundle myResources = ResourceBundle.getBundle("interactive/Pacman");

  private IntegerProperty heightProp;
  private IntegerProperty widthProp;

  private Pane overallPane = new Pane();
  private List<List<String>> toTextList = new ArrayList<>();
  private Stage myStage;


  public InteractiveLevelEditor(Stage stage){
    myStage = stage;
    topbar = new HBox(PADDING);
    completeGrid = new VBox(PADDING);
    bp = new BorderPane();
    widthProp = new SimpleIntegerProperty(START_VALUE);
    heightProp = new SimpleIntegerProperty(START_VALUE);
    setUpTopBar();
    bp.setTop(topbar);
    bp.setCenter(completeGrid);
    createInitialRows();
    overallPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
    overallPane.getChildren().add(bp);

  }

  public Pane getPane(){
    return overallPane;
  }

  private void setUpTopBar() {
    topbar.setPrefHeight(BUTTON_HEIGHT);
    setUpGameImagesBox();
    topbar.getChildren().addAll(createNumberField(heightProp, "Height: "), createNumberField(widthProp, "Width: "), gameImages, textFileNameArea());
    addListeners();
  }

  private HBox textFileNameArea(){
    HBox box = new HBox(PADDING);
    Text label = new Text("File Name: ");
    TextField textfield = new TextField();
    Button saveButton = new Button("Save Level Config");
    saveButton.setOnAction(e-> {
      if(textfield.getText().equals("")){
        throw new GameException("Empty file name");

      }
        for(Node n : completeGrid.getChildren()){
          HBox row = (HBox) n;
          List<String> rowList = new ArrayList<>();
          for(Node nn : row.getChildren()){
            CustomImageView nnn = (CustomImageView) nn;
            String[] regex = nnn.getPath().split("/");
            rowList.add(regex[regex.length - 1]); //get the image name
          }
          toTextList.add(rowList);
        }
        new CreateLevelFile(textfield.getText(), toTextList);
        toTextList.clear();
        backToStart();
    });
    System.out.println(textfield.getText());
    box.getChildren().addAll(label, textfield, saveButton);
    return box;
  }

  private void backToStart(){
    StartScreen myStartScreen = new StartScreen(myStage);
    myStage.setScene(myStartScreen.startScene());
    myStage.show();
  }

  private void addListeners(){
    addListenerToHeight();
    addListenerToWidth();
  }

  private void addListenerToHeight() {
    heightProp.addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        if(newValue.intValue() > oldValue.intValue()){
          int diff = newValue.intValue() - oldValue.intValue();
          System.out.println(diff);
          for(int i = oldValue.intValue(); i < newValue.intValue(); i++){
            HBox row = new HBox(PADDING);
            for(int j = 0; j < widthProp.getValue(); j++){
              CustomImageView iv = setDefaultImageView();
              row.getChildren().add(iv);
            }
            completeGrid.getChildren().add(row);
          }
        }
        else if(newValue.intValue() < oldValue.intValue()){
          for(int i = oldValue.intValue() - 1; i >= newValue.intValue(); i-- ){
            completeGrid.getChildren().remove(i);
          }
        }
      }
    });
  }

  private void addListenerToWidth(){
    widthProp.addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        if(newValue.intValue() > oldValue.intValue()){
          for(Node row : completeGrid.getChildren()){
            for(int i = oldValue.intValue(); i < newValue.intValue(); i++){
              HBox box = (HBox) row;
              box.getChildren().add(setDefaultImageView());

            }
          }
        }
        else if(newValue.intValue() < oldValue.intValue()){
          for(int i = oldValue.intValue() - 1; i >= newValue.intValue(); i-- ){
            for(Node row : completeGrid.getChildren()){
              HBox box = (HBox) row;
              box.getChildren().remove(i);
            }
          }
        }
      }
    });
  }

  private HBox createNumberField(IntegerProperty intProp, String str){
    HBox hbox = new HBox(PADDING);
    Button enterBtn = new Button("Enter");
    Text label = new Text(str);
    TextField textfield = new TextField(intProp.getValue() + "");
    enterBtn.setOnAction(e -> {
      try{
        int num = Integer.parseInt(textfield.getText());
        intProp.setValue(num);
        System.out.println(intProp.getValue());
      } catch(Exception ee){
        //todo add exception here
        throw new GameException("Please enter integer into height/width");
      }
    });
    hbox.getChildren().addAll(label, textfield, enterBtn);
    return hbox;
  }

  private void setUpGameImagesBox(){
    gameImages.setPrefWidth(BUTTON_WIDTH);

    for(String img : myResources.keySet()){
      gameImages.getItems().add(img);
    }

    gameImages.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
      @Override
      public ListCell<String> call(ListView<String> p) {
        final ListCell<String> cell = new ListCell<String>() {
          @Override
          protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(item);
            if (item == null || empty) {
              setGraphic(null);
            } else {
              Image icon = new Image(myResources.getString(item));
              ImageView iconImageView = new ImageView(icon);
              iconImageView.setFitHeight(IV_LENGTH);
              iconImageView.setPreserveRatio(true);
              setGraphic(iconImageView);
            }
          }
        };
        return cell;
      }
    });
    gameImages.getSelectionModel().selectFirst();
  }

  private void createInitialRows(){
    for(int i = 0; i < heightProp.getValue(); i++){
      HBox row = new HBox(PADDING);
      for(int j = 0; j < widthProp.getValue(); j++){
        CustomImageView iv;
        try{
          iv = setDefaultImageView();
        } catch (NullPointerException | IndexOutOfBoundsException e){
          iv = setDefaultImageView();
        }
        row.getChildren().add(iv);
      }
      completeGrid.getChildren().add(row);
    }
  }

  private CustomImageView setDefaultImageView() {
    Image image = new Image(myResources.getString("Block"));

    CustomImageView iv2 = new CustomImageView(image);
    iv2.setPath(myResources.getString("Block"));
    iv2.setFitWidth(IV_LENGTH);
    iv2.setFitHeight(IV_LENGTH);
    iv2.setImage(image);

    iv2.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      String newImageStr = gameImages.getValue().toString();
      Image newImage = new Image(myResources.getString(newImageStr));
      iv2.setImage(newImage);
      iv2.setPath(myResources.getString(newImageStr));
    });
    return iv2;
  }

}
