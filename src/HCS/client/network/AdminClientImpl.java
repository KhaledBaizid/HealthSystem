package HCS.client.network;

import HCS.server.network.RMIServer;
import HCS.shared.ClientCallBack;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
import HCS.shared.transferObjects.User;
//import HCS.shared.transferObjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

public class AdminClientImpl implements AdminClient, Remote //ClientCallBack
{
  private RMIServer server;
  private PropertyChangeSupport support;


  public AdminClientImpl() {
    support = new PropertyChangeSupport(this);
  }

  @Override
  public void startClient() {
    try {
      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)  registry.lookup("HCS");
     // server.registerClient(this);
     // System.out.println("StartClient");

    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }



  /*@Override public void login(User user)
  {
    try
    {
      if (server.loginUser(user)) {
        support.firePropertyChange(RequestType.SUCCESSFUL_LOGIN.toString(),null,user);
        support.firePropertyChange(RequestType.UPDATE_ACTIVE_USERS.toString(),null,user);

      } else{
        support.firePropertyChange(RequestType.EXISTING_USERNAME.toString(),null,user);

      }

      System.out.println("ClientLogin");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }
  

  @Override public ArrayList<String> getActiveUsersList()
  {
    try
    {
     return server.sendActiveUsersToClient();

    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void sendPublicMessage(Message messageToSend)
  {
    try
    {
      server.sendPublicMessage(messageToSend);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }*/

 /* @Override public void disconnect(User user)
  {
    try
    {
      server.unregisterClient(this);
      server.disconnect(user);

    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }*/

/*  @Override public void HCSLogin(String username, String password)
  {
    try
    { String role= server.HCSLogin(username,password);
      support.firePropertyChange("HCSLogin",null,role);


    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
   // return null;
  }*/

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {
    try
    {
      server.CreateUser(firstname, lastname, birthday, username, password, role);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void GetUsers()
  {

    try
    {
      System.out.println("client");
      ArrayList<User> users;
      users = server.GetUsers();
      support.firePropertyChange("HCSGetRoles",null, users);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void RemoveUser(String username)
  {
    try
    {
      server.RemoveUser(username);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }



 /* @Override
  public void publicMessageSent(PropertyChangeEvent event) {
    support.firePropertyChange(event);

  }

  @Override
  public void userAdded(PropertyChangeEvent event) {
    support.firePropertyChange(event);  }

  @Override public void userDeleted(PropertyChangeEvent event)

  {
    support.firePropertyChange(event);
  }*/

  /*@Override public void sharedroles(PropertyChangeEvent event)
      throws RemoteException
  {
    support.firePropertyChange(event);
  }*/

 /* @Override public void sharedBookings(PropertyChangeEvent event)
      throws RemoteException
  {

  }*/
}
