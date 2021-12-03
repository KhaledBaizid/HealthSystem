package HCS.client.network;

//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface AdminClient extends Subject
{
    void startClient();
    void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
   void   GetUsers();
    void RemoveUser(String username);

    //////
}
