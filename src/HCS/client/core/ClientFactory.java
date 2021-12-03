package HCS.client.core;

import HCS.client.network.*;
import HCS.shared.transferObjects.Booking;
/////import chat.client.network.Client;

public class ClientFactory
{
     AdminClient clientAdmin;
     ReceptionClient clientReception;
     LoginClient clientLogin;
     DoctorClient clientDoctor;
     BookingClient clientBooking;


    public AdminClient getAdminClient()
    {
        if (clientAdmin == null) clientAdmin = new AdminClientImpl();
        return clientAdmin;
    }
    public BookingClient getBookingClient()
    {
        if (clientBooking== null) clientBooking = new BookingClientImpl();
        return clientBooking;
    }


    //
    public ReceptionClient getReceptionClient()
    {
        if (clientReception == null) clientReception = new ReceptionClientImpl();
        return clientReception;
    }
    //
    public LoginClient getLoginClient()
    {
        if (clientLogin==null) clientLogin=new LoginClientImpl();
        return clientLogin;
    }
    //
    public DoctorClient getDoctorClient()
    {
        if (clientDoctor==null)clientDoctor=new DoctorClientImpl();
        return clientDoctor;
    }
}
