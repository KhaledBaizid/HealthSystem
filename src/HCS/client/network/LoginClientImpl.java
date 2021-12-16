package HCS.client.network;

import HCS.server.network.RMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class  LoginClientImpl implements LoginClient , Remote
{
  private RMIServer server;
  private PropertyChangeSupport support;

  public LoginClientImpl()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try {
      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)  registry.lookup("HCS");


    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override public void HCSLogin(String username, String password)
  {
    try
    {
      String role= server.Login(username,password);
      support.firePropertyChange("HCSLogin",null,role);
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
