package HCS.client.model;

import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface HCSModelAdminInterface extends Subject
{
   // void login(String username);
  // ArrayList<String> getActiveUsersList();
   // void sendPublicMessage(String value);
   // void disconnect();

    ///////
   void HCSLogin(String username,String password);
  void HCSCreateRole(String firstname,String lastname, Date birthday,String username,String password,String role);
    void   HCSGetRoles();
  void HCSRemoveRole(String username);
  ///////
}
