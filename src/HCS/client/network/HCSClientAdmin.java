package HCS.client.network;

import HCS.shared.transferObjects.Message;
import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface HCSClientAdmin extends Subject
{
    void startClient();

    void login(User user) ;

    ArrayList<String> getActiveUsersList();

    void sendPublicMessage(Message messageToSend);

    void disconnect(User user);


    //////
    void HCSLogin(String username,String password);
    void HCSCreateRole(String firstname,String lastname, Date birthday,String username,String password,String role);
   void   HCSGetRoles();
    void HCSRemoveRole(String username);

    //////
}
