package ooga.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Level {

    public Level(File level){
        createMapFromFile(level);
    }

    public void createMapFromFile(File file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String string;
            int row = 0;
            int ghostNum = 0;
            while ((string = br.readLine()) != null){
                for( int i = 0; i < string.length(); i++){
                    if (string.charAt(i) == 'x'){
//                        generateBlock(i, row);
                    } else if (string.charAt(i) == 'o') {
//                        generateFood(i , row);
                    } else if (string.charAt(i) == 'p'){
//                        generatePacMan(i, row);
                    } else if (string.charAt(i) == 'g'){
                        ghostNum++;
//                        generateGhost(i, row);
                    }
                }
                row++;
            }
        } catch(FileNotFoundException e){
            //TODO: add error here
            System.out.println("File not found");
        } catch (IOException e) {
            //TODO: add error here
            System.out.println(e);
        }
    }
}
