package HCS.client.network;

import HCS.server.network.RMIServerInterface;
import HCS.shared.ClientCallBack;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClientReception implements HCSClientReception,ClientCallBack
{
  private RMIServerInterface server;
  private PropertyChangeSupport support;

  public RMIClientReception ()
  {
    support = new PropertyChangeSupport(this);

  }

  @Override public void startClient()
  {
    try {
      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServerInterface)  registry.lookup("Chat");
      server.registerClient(this);
      // System.out.println("StartClient");

    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override public void createPatient(Patient patient)
  {
    try
    { System.out.println("ReceptionClient");
      server.createPatient(patient);
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

 /* @Override public void publicMessageSent(PropertyChangeEvent event)
      throws RemoteException
  {

  }

  @Override public void userAdded(PropertyChangeEvent event)
      throws RemoteException
  {

  }

  @Override public void userDeleted(PropertyChangeEvent event)
      throws RemoteException
  {

  }*/

  @Override public void sharedroles(PropertyChangeEvent event)
      throws RemoteException
  {
    support.firePropertyChange(event);
  }

  @Override public void HCSGetRoles()
  {

    try
    {
      System.out.println("client");
      ArrayList<Role> roles;
      roles = server.HCSGetRoles();
      support.firePropertyChange("HCSGetRoles",null,roles);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }
}
