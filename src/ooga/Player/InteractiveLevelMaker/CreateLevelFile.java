package ooga.Player.InteractiveLevelMaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import ooga.engine.GameException;

public class CreateLevelFile {
  private String fileName;
  private List<List<String>> myList;
  private ResourceBundle myResources = ResourceBundle.getBundle("interactive/ImageTranslateTableForTextFile");

  public CreateLevelFile(String str, List<List<String>> list){
    myList = list;
    String[] reg = str.split(".");
    if(!str.endsWith(".txt") || reg.length == 1){
      fileName = str + ".txt";
    }
    else{
      fileName = reg[0] + ".txt";
    }
    createFile();
  }

  private void createFile(){
    System.out.println("hih");
    try {
      FileWriter writer = new FileWriter(new File("data", fileName), false);
      for(int i = 0; i < myList.size(); i++){
        String currRow = "";
        for(int j = 0; j < myList.get(i).size(); j++){
          currRow += myResources.getString(myList.get(i).get(j));
        }
        writer.write(currRow);
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new GameException(e.getMessage());
    }

  }

}
