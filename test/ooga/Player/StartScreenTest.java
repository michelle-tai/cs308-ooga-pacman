package ooga.Player;

import javafx.scene.control.*;
import javafx.stage.Stage;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static javafx.beans.binding.Bindings.select;
import static org.junit.jupiter.api.Assertions.*;



class StartScreenTest extends ApplicationTest {

    private Button myButton;
    private ComboBox myComboBox;
    private Visualizer myVisualizer;
    private String currentGame;
    private Labeled myLabel;

    @BeforeEach
    void setUp() throws Exception{
        launch(Main.class);
        currentGame = "defaultPacMan";
        myButton = lookup("Start").queryButton();
        myComboBox = lookup("#paths").queryComboBox();
        myLabel = lookup("#Game").queryLabeled();
    }

    @Test
     void testButtonAction() {
        clickOn(myButton);
        assertNotNull(myVisualizer);
    }

    @Test
    void testComboBoxAction(){
        String curr = "defaultPacMan";
        select(myComboBox, curr);
        assertEquals(curr, myLabel.getText());
    }
}

