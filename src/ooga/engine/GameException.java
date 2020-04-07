package ooga.engine;

import javafx.scene.control.Alert;

/**
 * This class represents what might go wrong when using XML files.
 *
 * @author Robert C. Duvall
 */

public class GameException extends RuntimeException {

  // for serialization
  private static final long serialVersionUID = 1L;


  /**
   * Create an exception based on an issue in our code.
   */
  public GameException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   */
  public GameException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   */
  public GameException(Throwable cause) {
    showMessage(Alert.AlertType.ERROR, cause.getMessage());
  }

  public GameException(String error) {
    showMessage(Alert.AlertType.ERROR, error);
  }

  private void showMessage (Alert.AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }
}