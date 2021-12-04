package HCS.client.core;

import HCS.client.model.*;

public class ModelFactory
{
  private ClientFactory cf;
  AdminModel model;
  PatientModel modelReceptionInterface;
  LoginModel modelLoginInterface;
  DoctorModel modelDoctorInterface;
  BookingModel bookingModel;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public AdminModel getModelAdmin()
  {
    if (model == null) model = new AdminModelImpl(cf.getAdminClient());
    return model;
  }
  public BookingModel getModelBooking()
  {
    if (bookingModel == null) bookingModel = new BookingModelImpl(cf.getBookingClient());
    return bookingModel;
  }


  public PatientModel getModelReception()
  {
    if (modelReceptionInterface == null) modelReceptionInterface = new PatientModelImpl(cf.getReceptionClient());
    return modelReceptionInterface;
  }

  public LoginModel getModelLogin()
  {
    if (modelLoginInterface==null) modelLoginInterface=new LoginModelImpl(cf.getLoginClient());
        return modelLoginInterface;
  }

  public DoctorModel getModelDoctor()
  {
    if (modelDoctorInterface==null) modelDoctorInterface=new DoctorModelImpl(cf.getDoctorClient());
    return modelDoctorInterface;
  }


}
