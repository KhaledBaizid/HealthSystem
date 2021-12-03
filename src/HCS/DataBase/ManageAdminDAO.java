package HCS.DataBase;

import HCS.shared.transferObjects.Role;

import java.sql.Date;
import java.util.ArrayList;

public interface ManageAdminDAO
{
 // void createUser(String username);
  //String HCSLogin(String username,String password);
  boolean UserExist(String username);
  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
  ArrayList<Role> GetUsers();
  void RemoveUser(String username);
  void HCRUpdateRole(String firstname,String lastname, Date birthday,String username,String password,String role);

  //void createPatient(String cprnumber ,String firstname,String lastname);

}
