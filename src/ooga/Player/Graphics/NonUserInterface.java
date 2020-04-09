package ooga.Player.Graphics;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class NonUserInterface {

    public static final String RESOURCES = "resources";
    public static final String DEFAULT_RESOURCE_FOLDER = RESOURCES + "/formats/";
    public static final String ENGLISH_BUTTONS = "EnglishButtons";
    public static final String RULES_FILE = "src/resources/GameRules.txt";
    public static final int VBOX_SPACING = 10;

    private Styler styler;
    private ResourceBundle myResources;

    public NonUserInterface(){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_FOLDER + ENGLISH_BUTTONS);
        styler = new Styler(myResources);
    }

    public Node createComponents(){
        VBox vbox = new VBox(VBOX_SPACING);
        vbox.getChildren().addAll( styler.createLabel("DefaultRules"), readRules());
        vbox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
        return vbox;
    }

    private TextArea readRules(){
        TextArea rules = new TextArea();
        File file = new File(RULES_FILE);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                rules.appendText(line + "\n");
            }
        } catch(IOException e){
            //TODO catch the error
        }
        rules.setEditable(false);
        return rules;
    }
}
