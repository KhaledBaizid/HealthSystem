package HCS.server.model;

import HCS.Persistence.*;
import HCS.shared.transferObjects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserModelServerTest
{

  UserDAO adminDAO;
  Date date= new Date(-1970);

  @BeforeEach
  public void arrange() {

    adminDAO= UserDAO.getInstance();

  }

  @Test void userExist()
  {
    adminDAO.RemoveUser("yyyyyyy");
    adminDAO.CreateUser("administrator","administrator",date,"yyyyyy","yyyyyy","DOCTOR");
    boolean exist= adminDAO.UserExist("yyyyyy");
    assertTrue(exist);

    exist= adminDAO.UserExist("adgaagagagshshshshwyhxc");
    assertFalse(exist);

  }

  @Test void createUser()
  {
    adminDAO.RemoveUser("yyyyyy");
    adminDAO.CreateUser("administrator","administrator",date,"yyyyyy","adminstrator","RECEPTION");
    boolean exist= adminDAO.UserExist("yyyyyy");
    assertTrue(exist);
    adminDAO.RemoveUser("yyyyyy");
  }

  @Test void getUsers()
  {
    ArrayList<User> realUsers=adminDAO.GetUsers();
    String str="2015-03-31";
    Date date=Date.valueOf(str);

    String d= realUsers.get(0).getBirthday().toString();
    Date d1=Date.valueOf(d);

   // adminDAO.CreateUser("a","a",d1,"xxxxxx","aaa","ADMIN");
    adminDAO.CreateUser("r","r",date,"yyyyyy","rrr","RECEPTION");
    adminDAO.CreateUser("d","d",date,"zzzzzz","ddd","DOCTOR");

    ArrayList<User> testUsers=adminDAO.GetUsers();
    testUsers.removeAll(realUsers);
    assertTrue( testUsers.get(0).getUsername().equals("yyyyyy") && testUsers.get(1).getUsername().equals("zzzzzz") );
    assertFalse(testUsers.size()==1);
   // adminDAO.RemoveUser("xxxxxx");
    adminDAO.RemoveUser("yyyyyy");
    adminDAO.RemoveUser("zzzzzz");

  }

  @Test void removeUser()
  {
    adminDAO.CreateUser("a","a",date,"xxxxxx","aaa","RECEPTION");
    assertTrue(adminDAO.UserExist("xxxxxx"));
    adminDAO.RemoveUser("xxxxxx");
    assertTrue(!adminDAO.UserExist("xxxxxx"));

  }

}