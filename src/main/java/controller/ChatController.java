package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ChatBotEngine;
import model.GPTService;

public class ChatController {

    @FXML
    private TextField userInputField;

    @FXML
    private Button sendButton;

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane scrollPane;

    private final ChatBotEngine botEngine = new ChatBotEngine();

    @FXML
    public void initialize() {
        sendButton.setOnAction(e -> handleUserInput());
        userInputField.setOnAction(e -> handleUserInput());
    }

    private void handleUserInput() {
        String input = userInputField.getText().trim();
        if (input.isEmpty())
            return;

        addMessage(input, true);
        userInputField.clear();

        Label thinkingLabel = addMessage("Thinking...", false);

        new Thread(() -> {
            String reply;
            if (GPTService.isKeyAvailable()) {
                reply = GPTService.getResponse(input);
            } else {
                reply = botEngine.getResponse(input);
            }

            final String finalReply = reply;
            javafx.application.Platform.runLater(() -> {
                chatBox.getChildren().remove(thinkingLabel);
                addMessage(finalReply, false);
            });
        }).start();
    }

    private Label addMessage(String text, boolean isUser) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.getStyleClass().add(isUser ? "user-message" : "bot-message");

        HBox wrapper = new HBox(label);
        wrapper.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        wrapper.setPadding(new Insets(5, 10, 5, 10));

        chatBox.getChildren().add(wrapper);
        scrollPane.setVvalue(1.0);
        return label;
    }
}