package HCS.client.view;

import HCS.client.ViewModel.UserLoginViewModel;
import HCS.client.core.ViewHandler;
import HCS.shared.transferObjects.RequestType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.PropertyChangeEvent;


public class UserLoginController
{
  @FXML private TextField userNameTextField;
  @FXML private Label errorLabel;
  private ViewHandler vh;
  private UserLoginViewModel vm;
  String imputedUsername;

  public void init(ViewHandler vh, UserLoginViewModel vm)
  {
    this.vh = vh;
    this.vm=vm;
    vm.addListener(RequestType.SUCCESSFUL_LOGIN.toString(), this::successfulLogin);
    errorLabel.textProperty().bind(vm.errorProperty());
    userNameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
          onLoginButton();
      }
    });

  }

  public void onLoginButton()
  {
    imputedUsername = userNameTextField.textProperty().getValue();
    if (!imputedUsername.isEmpty()){
      vm.login(imputedUsername);
      System.out.println("ViewLogin");
    }
  }
  private void successfulLogin(PropertyChangeEvent event) {
    Platform.runLater(() -> {
      vh.openUserChatView(imputedUsername);
    });

  }
}
