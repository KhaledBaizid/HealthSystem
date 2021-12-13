package HCS.client.model;

import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface UserModel extends Subject
{

  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
  void   GetUsers();
  void RemoveUser(String username);

}
