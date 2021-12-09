package HCS.client.model;

import HCS.client.network.AdminClient;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
//import HCS.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;

public class AdminModelImpl implements AdminModel
{
    private AdminClient adminClient;
    private PropertyChangeSupport support;

    //private User user;


    public AdminModelImpl(AdminClient adminClient) {
        support = new PropertyChangeSupport(this);
        this.adminClient = adminClient;
        adminClient.startClient();
      //  user = new User();

       /* client.addListener(RequestType.SUCCESSFUL_LOGIN.toString(), this::fireAll);
        client.addListener(RequestType.EXISTING_USERNAME.toString(), this::fireAll);
        client.addListener(RequestType.UPDATE_ACTIVE_USERS.toString(), this::fireAll);
        client.addListener(RequestType.GET_ACTIVE_USERS.toString(), this::fireAll);
        client.addListener(RequestType.RECEIVE_PUBLIC.toString(), this::fireAll);*/
/////////////////////
        adminClient.addListener("HCSLogin", this::fireAll);
        adminClient.addListener("HCSGetRoles", this::fireAll);

        ////////////////////

    }

    private void fireAll(PropertyChangeEvent event) {
       support.firePropertyChange(event);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

  /*  @Override
    public void login(String username) {
        user.setUsername(username);
        client.login(user);
        System.out.println("ModelLogin");
    }

    @Override
    public ArrayList<String> getActiveUsersList() {
        return  client.getActiveUsersList();

    }

    @Override
    public void sendPublicMessage(String message) {
        Message messageToSend = new Message(message, user);
        client.sendPublicMessage(messageToSend);
    }

    @Override
    public void disconnect() {
        client.disconnect(user);
    }*/


  /*  @Override public void HCSLogin(String username, String password)
    {
         client.HCSLogin(username,password);
    }*/

    @Override public void CreateUser(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {
        adminClient.CreateUser(firstname, lastname, birthday, username, password, role);
        adminClient.GetUsers();
    }

    @Override public void GetUsers()
    {
        System.out.println("model");
        adminClient.GetUsers();
    }

    @Override public void RemoveUser(String username)
    {
        adminClient.RemoveUser(username);
    }
}
