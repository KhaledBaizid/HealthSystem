package HCS.shared;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientReceptionCallBack extends Remote
{
 // void patientHasBooking(PropertyChangeEvent event) throws RemoteException;
  void sharedPatients(PropertyChangeEvent event) throws RemoteException;
}