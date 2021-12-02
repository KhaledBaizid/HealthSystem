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
    private AdminClient client;
    private PropertyChangeSupport support;

    //private User user;


    public AdminModelImpl(AdminClient client) {
        support = new PropertyChangeSupport(this);
        this.client = client;
        client.startClient();
      //  user = new User();

       /* client.addListener(RequestType.SUCCESSFUL_LOGIN.toString(), this::fireAll);
        client.addListener(RequestType.EXISTING_USERNAME.toString(), this::fireAll);
        client.addListener(RequestType.UPDATE_ACTIVE_USERS.toString(), this::fireAll);
        client.addListener(RequestType.GET_ACTIVE_USERS.toString(), this::fireAll);
        client.addListener(RequestType.RECEIVE_PUBLIC.toString(), this::fireAll);*/
/////////////////////
        client.addListener("HCSLogin", this::fireAll);
        client.addListener("HCSGetRoles", this::fireAll);

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


    @Override public void HCSLogin(String username, String password)
    {
         client.HCSLogin(username,password);
    }

    @Override public void HCSCreateRole(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {
        client.HCSCreateRole(firstname, lastname, birthday, username, password, role);
       client.HCSGetRoles();
    }

    @Override public void HCSGetRoles()
    {
        System.out.println("model");
        client.HCSGetRoles();
    }

    @Override public void HCSRemoveRole(String username)
    {
        client.HCSRemoveRole(username);
    }
}
