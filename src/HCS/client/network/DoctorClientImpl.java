package HCS.client.network;

import HCS.server.network.RMIServer;
import HCS.shared.ClientCallBack;
import HCS.shared.transferObjects.Booking;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DoctorClientImpl implements DoctorClient, ClientCallBack
{
  private RMIServer server;
  private PropertyChangeSupport support;

  public DoctorClientImpl()
  {
    this.support=new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try {
      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)  registry.lookup("HCS");
      server.registerClient(this);
      // System.out.println("StartClient");

    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
    
  }

  @Override public void HCSGetBookings()
  {
    try
    {
      ArrayList<Booking> bookings;
      bookings= server.GetBookings();
      support.firePropertyChange("HCSGetBookings",null,bookings);
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

/*  @Override public void publicMessageSent(PropertyChangeEvent event)
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

  /*@Override public void sharedroles(PropertyChangeEvent event)
      throws RemoteException
  {

  }*/

  @Override public void sharedBookings(PropertyChangeEvent event)
      throws RemoteException
  {
    support.firePropertyChange(event);

  }
}
