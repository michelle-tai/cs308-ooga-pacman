package ooga.Player;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static java.rmi.Naming.lookup;
import static javafx.application.Application.launch;

class StartScreenTest {

    private Button myButton;
    private ComboBox myComboBox;
    private Hyperlink myHyperLink;

    @BeforeEach
    public void setUp() throws Exception{
        launch(Main.class);
//        myButton = lookup("#Start").;

    }


    @Test
    void startScene() {
    }
}