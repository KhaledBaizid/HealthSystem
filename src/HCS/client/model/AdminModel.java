package HCS.client.model;

import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface AdminModel extends Subject
{
   // void login(String username);
  // ArrayList<String> getActiveUsersList();
   // void sendPublicMessage(String value);
   // void disconnect();

    ///////
  // void HCSLogin(String username,String password);
  void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
    void   GetUsers();
  void RemoveUser(String username);
  ///////
}
