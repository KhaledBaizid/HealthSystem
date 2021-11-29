package HCS.client.core;

//import HCS.client.view.*;
import HCS.client.view.ADMIN.HCSAdminController;
import HCS.client.view.DOCTOR.HCSDoctorController;
import HCS.client.view.LOGIN.HCSLoginController;
import HCS.client.view.RECEPTION.HCSReceptionistController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory vmf;

  private Stage primaryStage;
   Scene loginScene;
   Scene UserChatScene;
   Scene DeleteRole;
   Scene ReceptionScene;
   Scene DoctorScene;

  public ViewHandler(ViewModelFactory vmf, Stage primaryStage)
  {
    this.vmf = vmf;
    this.primaryStage = primaryStage;
    primaryStage.setResizable(false);
  }

  public void start()
  {
    //openUserLogin();

  openHCSLogin();

  }


 /* public void openUserChatView(String username)
  {
    if (UserChatScene == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserChatView.fxml"));
        Parent root = loader.load();
        UserChatController ctrl = loader.getController();
        ctrl.init(this,vmf.getUserChatViewModel());
        UserChatScene = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    primaryStage.setTitle(username);
    primaryStage.setScene(UserChatScene);
    primaryStage.show();

  }*/


 /* public void openUserLogin()
  {

    if (loginScene == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserLogin.fxml"));
        Parent root = loader.load();
        UserLoginController ctrl = loader.getController();
        ctrl.init(this,vmf.getUserLoginViewModel());
        loginScene = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("Login");
    primaryStage.setScene(loginScene);
    primaryStage.show();
  }*/

  /////

  public void openHCSLogin()
  {

    if (loginScene == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LOGIN/HCSLogin.fxml"));
        Parent root = loader.load();
        HCSLoginController ctrl = loader.getController();
        ctrl.init(this,vmf.getHcsLoginViewModel());
        loginScene = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("Login");
    primaryStage.setScene(loginScene);
    primaryStage.show();
  }

  /////

  public void openHCSAdmin(String username)
  {

    if (UserChatScene  == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ADMIN/HCSAdmin.fxml"));
        Parent root = loader.load();
        HCSAdminController ctrl = loader.getController();
        ctrl.init(this,vmf.getHcsAdminViewModel());
        UserChatScene  = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("ADMIN: "+username);
    primaryStage.setScene(UserChatScene);
    primaryStage.show();
  }
///////////

  public void openHCSReceptionist (String username)
  {

    if (ReceptionScene  == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/RECEPTION/HCSReceptionist.fxml"));
        Parent root = loader.load();
        HCSReceptionistController ctrl = loader.getController();
        ctrl.init(this,vmf.getHcsReceptionistViewModel());
        ReceptionScene  = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("RECEPTIONIST: "+username);
    primaryStage.setScene(ReceptionScene);
    primaryStage.show();
  }

  ///
  public void openHCSDoctor (String username)
  {

    if (DoctorScene == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/DOCTOR/HCSDoctor.fxml"));
        Parent root = loader.load();
        HCSDoctorController ctrl = loader.getController();
        ctrl.init(this,vmf.getHcsDoctorViewModel());
        DoctorScene = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("DOCTOR: "+username);
    primaryStage.setScene(DoctorScene);
    primaryStage.show();
  }

 /* public void openHCSAdminDeleteRole ()
  {

    if (DeleteRole  == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/HCSAdminDeleteRole.fxml"));
        Parent root = loader.load();
        HCSAdminDeleteRoleController ctrl = loader.getController();
        ctrl.init(this,vmf.getHcsAdminDeleteRoleViewModel());
        DeleteRole  = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("Delete: ");
    primaryStage.setScene(DeleteRole);
    primaryStage.show();
  }*/





}
