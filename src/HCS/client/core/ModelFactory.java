package HCS.client.core;

import HCS.client.model.*;

public class ModelFactory
{
  private ClientFactory cf;
  UserModel userModel;
  PatientModel patientModel;
  LoginModel loginModel;
  PrescriptionModel prescriptionModel;
  BookingModel bookingModel;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public UserModel getModelAdmin()
  {
    if (userModel == null) userModel = new UserModelImpl(cf.getAdminClient());
    return userModel;
  }
  public BookingModel getModelBooking()
  {
    if (bookingModel == null) bookingModel = new BookingModelImpl(cf.getBookingClient());
    return bookingModel;
  }


  public PatientModel getModelPatient()
  {
    if (patientModel == null) patientModel = new PatientModelImpl(cf.getPatientClient());
    return patientModel;
  }

  public LoginModel getModelLogin()
  {
    if (loginModel==null) loginModel=new LoginModelImpl(cf.getLoginClient());
        return loginModel;
  }

  public PrescriptionModel getModelPrescription()
  {
    if (prescriptionModel==null) prescriptionModel=new PrescriptionModelImpl(cf.getPrescriptionClient());
    return prescriptionModel;
  }


}
