package HCS.client.model;

import HCS.client.network.UserClient;
import HCS.shared.transferObjects.User;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
//import HCS.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;

public class UserModelImpl implements UserModel
{
    private UserClient userClient;
    private PropertyChangeSupport support;




    public UserModelImpl(UserClient userClient) {
        support = new PropertyChangeSupport(this);
        this.userClient = userClient;
        userClient.startClient();

        userClient.addListener("HCSGetRoles", this::fireUsers);



    }

    private void fireUsers(PropertyChangeEvent event) {
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

    @Override public void CreateUser(String firstname, String lastname,
        Date birthday, String username, String password, String role)
    {
        userClient
            .CreateUser(firstname, lastname, birthday, username, password, role);
        userClient.GetUsers();
    }

    @Override public void GetUsers()
    {
        System.out.println("model");
        userClient.GetUsers();
    }

    @Override public void RemoveUser(String username)
    {
        userClient.RemoveUser(username);
    }

    @Override public boolean UserExist(String username)
    {
        return userClient.UserExist(username);
    }

    @Override public void updateUser(String username, User user)
    {
     userClient.updateUser(username, user);
     userClient.GetUsers();
    }
}
