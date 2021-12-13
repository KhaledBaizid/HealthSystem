package HCS.client.core;

//import HCS.client.view.*;
import HCS.client.view.ADMIN.HCSAdminController;
//import HCS.client.view.Booking.HCSBookingController;
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
   Scene AdmintScene;

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

  openHCSLogin();

  }

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
        ctrl.init(this,vmf);
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

    if (AdmintScene  == null)
    {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ADMIN/HCSAdmin.fxml"));
        Parent root = loader.load();
        HCSAdminController ctrl = loader.getController();
        ctrl.init(this,vmf.getHcsAdminViewModel());
        AdmintScene  = new Scene(root);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    primaryStage.setTitle("ADMIN: "+username);
    primaryStage.setScene(AdmintScene);
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





}
