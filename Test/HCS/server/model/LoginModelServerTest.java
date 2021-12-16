package HCS.server.model;

import HCS.Persistence.UserDAO;
import HCS.Persistence.LoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoginModelServerTest
{
  LoginDAO loginDAO;
  UserDAO adminDAO;
  Date date= new Date(-1970);
  @BeforeEach
  public void arrange() {

    loginDAO = LoginDAO.getInstance();
     adminDAO= UserDAO.getInstance();
  }

  @Test void login()
  {

    adminDAO.RemoveUser("RECEPTIONIST");
    adminDAO.RemoveUser("DOCTOR");

    adminDAO.CreateUser("receptionist","receptionist",date,"RECEPTIONIST","receptionist","RECEPTION");
    adminDAO.CreateUser("Doc","Doc",date,"DOCTOR","Doc","DOCTOR");

    String role;
    role= loginDAO.Login("RECEPTIONIST","receptionist");
    assertTrue(role.equals("RECEPTION"));

    role= loginDAO.Login("DOCTOR","Doc");
    assertTrue(role.equals("DOCTOR"));

    role= loginDAO.Login("bbbb","abbb");
    assertTrue(role==null);

    adminDAO.RemoveUser("RECEPTIONIST");
    adminDAO.RemoveUser("DOCTOR");


  }
}