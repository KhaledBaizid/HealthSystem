package HCS.DataBase;

import HCS.shared.transferObjects.User;

import java.sql.Date;
import java.util.ArrayList;

public interface ManageUserDAO
{

  boolean UserExist(String username);
  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
  ArrayList<User> GetUsers();
  void RemoveUser(String username);
  void HCRUpdateRole(String firstname,String lastname, Date birthday,String username,String password,String role);

  void deleteAllUsers();



}
