package HCS.server.model;

import HCS.DataBase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServerServerModelImplTest
{ ServerModelImpl server;
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
    exist=adminDAO.UserExist("a");
    System.out.println(exist);
    assertTrue(exist);
  }

  @Test void HCSCreateRole()
  {
    Date date= new Date(02-10-1982);
    boolean executed=false;
    if (adminDAO.UserExist("z"))
    {

      assertTrue(!executed);
    }
    else {
      adminDAO.CreateUser("r", "r", date, "z", "r", "RECEPTION");
      assertTrue(!executed);
    }

  }

  @Test void HCSLogin()
  {
    String role=loginDAO.Login("a","a");
    assertTrue(role.equals("ADMIN"));
    role=loginDAO.Login("r","r");
    assertTrue(role.equals("RECEPTION"));
    role=loginDAO.Login("d","d");
    assertTrue(role.equals("DOCTOR"));
    role=loginDAO.Login("k","a");
    assertTrue(role==null);
   /* Date date= new Date(02-10-1982);
    adminDAO.HCSCreateRole("r", "r", date, "b", "a", "RECEPTION");
    assertTrue(role.equals("RECEPTION"));*/

  }

  @Test void testRoleExist()
  {
  }
}