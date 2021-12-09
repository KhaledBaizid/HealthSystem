package HCS.server.model;

import HCS.DataBase.AdminDAO;
import HCS.DataBase.LoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoginModelServerTest
{
  LoginDAO loginDAO;
  AdminDAO adminDAO;
  Date date= new Date(-1970);
  @BeforeEach
  public void arrange() {

    loginDAO= LoginDAO.getInstance();
     adminDAO=AdminDAO.getInstance();
  }

  @Test void login()
  {
    adminDAO.RemoveUser("administrator");
    adminDAO.RemoveUser("receptionist");
    adminDAO.RemoveUser("Doc");

    adminDAO.CreateUser("administrator","adminustrator",date,"administrator","adminstrator","ADMIN");
    adminDAO.CreateUser("receptionist","receptionist",date,"receptionist","receptionist","RECEPTION");
    adminDAO.CreateUser("Doc","Doc",date,"Doc","Doc","DOCTOR");

    String role=loginDAO.Login("administrator","adminstrator");
    assertTrue(role.equals("ADMIN"));

    role=loginDAO.Login("receptionist","receptionist");
    assertTrue(role.equals("RECEPTION"));

    role=loginDAO.Login("Doc","Doc");
    assertTrue(role.equals("DOCTOR"));

    role=loginDAO.Login("bbbb","abbb");
    assertTrue(role==null);
    adminDAO.RemoveUser("administrator");
    adminDAO.RemoveUser("receptionist");
    adminDAO.RemoveUser("Doc");



  }
}