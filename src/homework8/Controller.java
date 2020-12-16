package homework8;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class Controller {
    private final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
            "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private String secret, word;
    private final StringBuffer sb = new StringBuffer("###############");;

    @FXML
    private Label menuLabel;

    @FXML
    private Label secretLabel;

    @FXML
    private Label helpLabel;

    @FXML
    private Label promptLabel;

    @FXML
    private TextField inputField;
    @FXML
    private Button guessButton;

    @FXML
    public void initialize() {
        menuLabel.setText("Программа загадала слово, попробуй его отгадать\n" +
                "Загаданное слово это фрукт, овощь или ягода\n(слово на английском языке)");
        promptLabel.setText("Введите слово или букву:");
        secretLabel.setText("Слово: " + sb);
        secret = words[new Random().nextInt(words.length)];
        guessButton.setDisable(true);
        helpLabel.setText("");
    }

    @FXML
    void onExitAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Действительно выйти?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    @FXML
    void onGuessAction() {
        word = inputField.getText();
        inputField.clear();
        if (word.equalsIgnoreCase(secret)) {
            secretLabel.setText("Вы угадали!");
        } else {
            if(word.length() == 1) {
                for (int i = 0; i < secret.length(); i++) {
                    if(secret.charAt(i) == word.charAt(0)) {
                        sb.deleteCharAt(i);
                        sb.insert(i, word.charAt(0));
                    }
                }
            } else {
                for (int i = 0; i < secret.length(); i++) {
                    if (i >= word.length()) {
                        break;
                    }
                    if (secret.charAt(i) == word.charAt(i)) {
                        sb.deleteCharAt(i);
                        sb.insert(i, word.charAt(i));
                    }
                }
            }
            secretLabel.setText("Слово: " + sb);
        }
    }

    public void onInputAction() {
        if(inputField.getText().isBlank()) {
            guessButton.setDisable(true);
        } else {
            guessButton.setDisable(false);
        }
    }

    public void onHelpShowAction() {
        helpLabel.setText("apple, orange, lemon, banana, apricot, avocado, broccoli, \n" +
                          "carrot, cherry, garlic, grape, melon, leak, kiwi, mango,\n" +
                          "mushroom, nut, olive, pea, peanut, pear, pepper, potato,\n" +
                          "pineapple, pumpkin");
    }

    public void onHelpHideAction() {
        helpLabel.setText("");
    }
}