package HCS.server.model;

import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface AdminModelServer extends Subject
{
  boolean UserExist(String username);
  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
  ArrayList<User> GetUsers();
  void RemoveUser(String username);

}
