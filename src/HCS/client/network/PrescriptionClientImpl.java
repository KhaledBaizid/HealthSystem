package HCS.client.network;

import HCS.server.network.RMIServer;
import HCS.shared.BookingClientCallBack;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.PrescriptionType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

public class PrescriptionClientImpl
    implements PrescriptionClient, BookingClientCallBack
{
  private RMIServer server;
  private PropertyChangeSupport support;

  public PrescriptionClientImpl()
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

 /* @Override public void HCSGetBookings()
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
  }*/

  @Override public void createPrescription(Prescription prescription)
  {
    try
    {
      server.createPrescription(prescription);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void getPrescriptions()
  {
    try
    {
      ArrayList<Prescription> prescriptions;
      prescriptions= server.getPrescriptions();
      support.firePropertyChange("HCSGetPrescriptions",null,prescriptions);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removePrescription(Prescription prescription)
  {
    try
    {
      server.removePrescription(prescription);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void updatePrescription(Date bookingDate, String bookingTime,
      String prescriptionType, String newPrescriptionType,
      String prescriptionText)
  {
    try
    {
      server.updatePrescription(bookingDate, bookingTime, prescriptionType, newPrescriptionType, prescriptionText);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void getPrescriptionsByPatient(
      String cprNumber)
  {
    try
    {
      ArrayList<Prescription> prescriptions;
      prescriptions= server.getPrescriptionsByPatient(cprNumber);
      support.firePropertyChange("HCSGetPrescriptions",null,prescriptions);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void getPrescriptionsByDate(Date date)
  {
    try
    {
      ArrayList<Prescription> prescriptions;
      prescriptions= server.getPrescriptionsByDate(date);
      support.firePropertyChange("HCSGetPrescriptions",null,prescriptions);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<String> getPrescriptionsType()
  {
    try
    {
      return server.getPrescriptionsType();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
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
