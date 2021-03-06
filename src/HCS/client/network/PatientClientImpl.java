package HCS.client.network;

import HCS.shared.PatientClientCallBack;
import HCS.server.network.RMIServer;
import HCS.shared.BookingClientCallBack;
import HCS.shared.transferObjects.Patient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PatientClientImpl
    implements PatientClient, PatientClientCallBack
{
  private RMIServer server;
  private PropertyChangeSupport support;

  public PatientClientImpl()
  {
    support = new PropertyChangeSupport(this);


  }

  @Override public void startClient()
  {
    try {
      UnicastRemoteObject.exportObject( this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer)  registry.lookup("HCS");

      server.registerPatientClient(this);


    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override public void Disconnect()
  {
    try
    {
      server.unregisterPatientClient(this);
    }
    catch (RemoteException e)
    {
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

  @Override public void removePatient(String cprNumber)
  {
    try
    { System.out.println("ReceptionClient");
     // server.
      server.removePatient(cprNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void updatePatient(String cprNumber, Patient patient)
  {
    try
    { System.out.println("ReceptionClient");
      server.updatePatient(cprNumber, patient);
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

  @Override public void GetPatients()
  {
    ArrayList<Patient> patients;
    try
    {
      patients=server.GetPatients();
      support.firePropertyChange("HCSGetPatients",null,patients);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void GetSpecificPatients(String search)
  {
    ArrayList<Patient> patients;
    try
    {
      patients=server.GetSpecificPatients(search);
      support.firePropertyChange("HCSGetPatients",null,patients);


    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public boolean patientExist(String cprNumber)
  {
    try
    {
      return server.patientExist(cprNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }


  @Override public void sharedPatients(PropertyChangeEvent event)
      throws RemoteException
  {
    support.firePropertyChange(event);
  }
}
