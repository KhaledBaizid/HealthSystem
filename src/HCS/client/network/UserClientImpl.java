package HCS.client.network;

import HCS.server.network.RMIServer;
//import HCS.shared.transferObjects.Message;
//import HCS.shared.transferObjects.RequestType;
import HCS.shared.transferObjects.User;
//import HCS.shared.transferObjects.User;

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

public class UserClientImpl implements UserClient, Remote
{
  private RMIServer server;
  private PropertyChangeSupport support;


  public UserClientImpl() {
    support = new PropertyChangeSupport(this);
  }

  @Override
  public void startClient() {
    try {

      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)  registry.lookup("HCS");


    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }


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



}
