package HCS.shared;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PatientClientCallBack extends Remote
{

  void sharedPatients(PropertyChangeEvent event) throws RemoteException;
}
