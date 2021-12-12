package HCS.client.core;

import HCS.client.network.*;
/////import chat.client.network.Client;

public class ClientFactory
{
     UserClient clientAdmin;
     PatientClient clientPatient;
     LoginClient clientLogin;
     PrescriptionClient clientPrescription;
     BookingClient clientBooking;


    public UserClient getAdminClient()
    {
        if (clientAdmin == null) clientAdmin = new UserClientImpl();
        return clientAdmin;
    }
    public BookingClient getBookingClient()
    {
        if (clientBooking== null) clientBooking = new BookingClientImpl();
        return clientBooking;
    }


    //
    public PatientClient getPatientClient()
    {
        if (clientPatient == null) clientPatient = new PatientClientImpl();
        return clientPatient;
    }
    //
    public LoginClient getLoginClient()
    {
        if (clientLogin==null) clientLogin=new LoginClientImpl();
        return clientLogin;
    }
    //
    public PrescriptionClient getPrescriptionClient()
    {
        if (clientPrescription==null)clientPrescription=new PrescriptionClientImpl();
        return clientPrescription;
    }
}
