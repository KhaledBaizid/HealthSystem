package HCS.client.network;

import HCS.DataBase.LoginDAO;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginClientTest
{
  LoginDAO loginDAO;
  LoginClientImpl client = new LoginClientImpl();

  @BeforeEach
  public void arrange() {

 loginDAO= LoginDAO.getInstance();

  }

  @org.junit.jupiter.api.Test void HCSLogin()
  {
   //loginDAO.HCSLogin("b","a");
    String role=loginDAO.HCSLogin("a","a");
    assertTrue(role.equals("ADMIN"));
    role=loginDAO.HCSLogin("r","r");
    assertTrue(role.equals("RECEPTION"));
    role=loginDAO.HCSLogin("d","d");
    assertTrue(role.equals("DOCTOR"));
    role=loginDAO.HCSLogin("b","a");
    assertTrue(role==null);

  }
}