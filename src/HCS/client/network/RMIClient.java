package HCS.client.network;

public interface RMIClient
{
  LoginClient getLoginClient();
  AdminClient getAdminClient();
  PatientClient getPatientClient();
  BookingClient getBookingClient();
  PrescriptionClient getPrescriptionClient();


}
