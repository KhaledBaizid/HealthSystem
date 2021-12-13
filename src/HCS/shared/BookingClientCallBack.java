package HCS.shared;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookingClientCallBack extends Remote {

    void sharedBookings(PropertyChangeEvent event) throws RemoteException;



}
