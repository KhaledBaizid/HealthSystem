package HCS.client.view.LOGIN;

import HCS.client.ViewModel.LoginViewModel;
import HCS.client.core.ViewHandler;
import HCS.client.core.ViewModelFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;

public class HCSLoginController
{
  @FXML
  private TextField HCSUsernameTextEdit;
  @FXML
  private TextField HCSPasswordTextEdit;
  @FXML
  private PasswordField HCSPassword;
  @FXML
  private Label errorLabel;

  private ViewHandler vh;
  private LoginViewModel vm;
  private ViewModelFactory vmf;

  public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    this.vm=vmf.getHcsLoginViewModel();
    vm.addListener("HCSLogin",this::succesfulLogin);
    errorLabel.textProperty().bind(vm.errorProperty());
    //HCSPasswordTextEdit.visibleProperty().bind(HCSPasswordTextEdit.selectedProperty().not());
  }

  private void succesfulLogin(PropertyChangeEvent event)
  {
    String page= (String)event.getNewValue();
    System.out.println(page+page);
    if (page.equals("ADMIN"))
    {
      Platform.runLater(() -> {
       vh.openHCSAdmin(HCSUsernameTextEdit.textProperty().getValue());
      });
    } else if (page.equals("RECEPTION"))
    {
      Platform.runLater(() -> {
        vh.openHCSReceptionist(HCSUsernameTextEdit.textProperty().getValue());
      });
    } else{
      Platform.runLater(() -> {
        vh.openHCSDoctor(HCSUsernameTextEdit.textProperty().getValue());
      });

    }

  }

  public void HCSLoginButton()
  {
   vm.HCSLogin(HCSUsernameTextEdit.textProperty().getValue(),HCSPassword.textProperty().getValue());
    System.out.println("viewcontroller");
  }
}
