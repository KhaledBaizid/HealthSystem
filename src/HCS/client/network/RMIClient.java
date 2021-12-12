package HCS.client.network;

public interface RMIClient
{
  LoginClient getLoginClient();
  UserClient getAdminClient();
  PatientClient getPatientClient();
  BookingClient getBookingClient();
  PrescriptionClient getPrescriptionClient();


}
