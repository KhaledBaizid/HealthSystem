package HCS.client.view;

import HCS.client.ViewModel.UserChatViewModel;
import HCS.client.core.ViewHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UserChatController
{
  @FXML
  private ListView<String> users;
  @FXML
  private TextArea textArea;
  @FXML
  private TextArea chatTextArea;
  @FXML
  private TextField messageInputField;

  private ViewHandler vh;
  private UserChatViewModel vm;

  public void init(ViewHandler vh, UserChatViewModel vm)
  {
    this.vh = vh;
    this.vm = vm;
    chatTextArea.textProperty().bind(vm.updatemessagearea());
    messageInputField.textProperty().bindBidirectional(vm.getSentMessageProperty());
    users.setItems(vm.getActiveUsersList());
    messageInputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
          onSendButton();
      }
    });
    vm.getActiveUsersList();
  }


  public void onSendButton() {
    String imputedMessage = messageInputField.textProperty().getValue();

    if (!imputedMessage.isEmpty()) {
      vm.sendPublicMessage();
      messageInputField.textProperty().setValue("");
    }
  }

  public void onDisconnectButton() {

    vm.disconnect();
    System.exit(0);

  }
}
