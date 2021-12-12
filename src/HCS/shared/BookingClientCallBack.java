package HCS.shared;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookingClientCallBack extends Remote {
  //  void publicMessageSent(PropertyChangeEvent event) throws RemoteException;

   // void userAdded(PropertyChangeEvent event) throws RemoteException;

    //void userDeleted(PropertyChangeEvent event) throws RemoteException;

   // void sharedroles(PropertyChangeEvent event) throws RemoteException;
    void sharedBookings(PropertyChangeEvent event) throws RemoteException;
  //  void patientHasBooking(PropertyChangeEvent event) throws RemoteException;


}
