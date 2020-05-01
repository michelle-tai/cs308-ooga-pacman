package ooga.Player.InteractiveLevelMaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import ooga.engine.GameException;

/**
 * Writes a text file that is saved into the data folder. The text file be used in any game.
 * The constructor requires a String name for the file, and a List of strings which is then used as keys to a
 * property file to write the corresponding character to the text file. While this isn't the best design, since I
 * am showing the implementation of the List, I could change it so that it would accept an object instead of a
 * list, thus hiding the implementation.
 * @author Michelle Tai
 */

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
