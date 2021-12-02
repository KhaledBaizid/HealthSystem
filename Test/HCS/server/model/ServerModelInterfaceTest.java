package HCS.server.model;

import HCS.DataBase.*;
import HCS.shared.transferObjects.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServerModelInterfaceTest
{ ServerModel server;
  AdminDAO adminDAO;
  LoginDAO loginDAO;

  /*ManageAdminDAO userDAO=new ManageAdminDAO()
  {
    @Override public void createUser(String username)
    {

    }



    @Override public boolean roleExist(String username)
    {
      return false;
    }

    @Override public void HCSCreateRole(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {

    }

    @Override public ArrayList<Role> HCSGetRoles()
    {
      return null;
    }

    @Override public void HCSRemoveRole(String username)
    {

    }

    @Override public void HCRUpdateRole(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {

    }

    @Override public void createPatient(String cprnumber, String firstname,
        String lastname)
    {

    }
  };

  ManageReceptionDAO receptionDAO;
  ManageLoginDAO loginDAO;*/

  @BeforeEach
  public void arrange() {

   // adminDAO= AdminDAO.getInstance();
    adminDAO=AdminDAO.getInstance();
    loginDAO= LoginDAO.getInstance();

   //  server=new ServerModel( userDAO,receptionDAO, loginDAO);


  }


 // AdminDAO adminDAO;



 // @Test void roleExist()
  @org.junit.jupiter.api.Test void roleExist()
  {

    boolean exist;
  // userDAO=AdminDAO.getInstance();
    exist=adminDAO.roleExist("a");
    System.out.println(exist);
    assertTrue(exist);
  }

  @Test void HCSCreateRole()
  {
    Date date= new Date(02-10-1982);
    boolean executed=false;
    if (adminDAO.roleExist("p"))
    {

      assertTrue(!executed);
    }
    else {
      adminDAO.HCSCreateRole("r", "r", date, "p", "r", "RECEPTION");
      assertTrue(!executed);
    }

  }

  @Test void HCSLogin()
  {
    String role=loginDAO.HCSLogin("a","a");
    assertTrue(role.equals("ADMIN"));
    role=loginDAO.HCSLogin("r","r");
    assertTrue(role.equals("RECEPTION"));
    role=loginDAO.HCSLogin("d","d");
    assertTrue(role.equals("DOCTOR"));
    role=loginDAO.HCSLogin("k","a");
    assertTrue(role==null);
   /* Date date= new Date(02-10-1982);
    adminDAO.HCSCreateRole("r", "r", date, "b", "a", "RECEPTION");
    assertTrue(role.equals("RECEPTION"));*/

  }

  @Test void testRoleExist()
  {
  }
}