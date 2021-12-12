package HCS.client.network;

public class RMIClientImpl implements RMIClient

{
 private LoginClient loginClient;
 private UserClient userClient;
 private PatientClient patientClient;
 private BookingClient bookingClient;
 private PrescriptionClient prescriptionClient;

  public RMIClientImpl()
  {


  }
  @Override public LoginClient getLoginClient()
  {
    if (loginClient == null) loginClient = new LoginClientImpl();
    return loginClient;
  }

  @Override public UserClient getAdminClient()
  {
    if (userClient == null) userClient = new UserClientImpl();
    return userClient;
  }

  @Override public PatientClient getPatientClient()
  {
    if (patientClient == null) patientClient = new PatientClientImpl();
    return patientClient;
  }

  @Override public BookingClient getBookingClient()
  {
    if (bookingClient == null) bookingClient = new BookingClientImpl();
    return bookingClient;
  }

  @Override public PrescriptionClient getPrescriptionClient()
  {
    if (prescriptionClient == null) prescriptionClient = new PrescriptionClientImpl();
    return prescriptionClient;
  }
}
