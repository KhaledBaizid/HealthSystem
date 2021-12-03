package HCS.client.core;

import HCS.client.ViewModel.*;

public class ViewModelFactory
{
  private ModelFactory mf;
 // private UserChatViewModel userChatViewModel;
  //private UserLoginViewModel userloginviewmodel;

   LoginViewModel loginViewModel;
   AdminViewModel adminViewModel;
   ReceptionViewModel receptionViewModel;
   HCSAdminDeleteRoleViewModel hcsAdminDeleteRoleViewModel;
   DoctorViewModel doctorViewModel;
   BookingViewModel bookingViewModel;

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
  }

  public LoginViewModel getHcsLoginViewModel()
  {

    if (loginViewModel ==null)
      loginViewModel = new LoginViewModel(mf.getModelLogin());
    return loginViewModel;
  }

  public BookingViewModel getHcsBookingViewModel()
  {

    if (bookingViewModel ==null)
      bookingViewModel = new BookingViewModel(mf.getModelBooking());
    return bookingViewModel;
  }

  public AdminViewModel getHcsAdminViewModel()
  {
    if (adminViewModel ==null)
      adminViewModel = new AdminViewModel(mf.getModelAdmin());
    return adminViewModel;
  }

  public ReceptionViewModel getHcsReceptionistViewModel()
  {
    if (receptionViewModel ==null)
      receptionViewModel = new ReceptionViewModel(
           mf.getModelReception(), mf.getModelBooking());
    return receptionViewModel;
  }

  public HCSAdminDeleteRoleViewModel getHcsAdminDeleteRoleViewModel()
  {
    if (hcsAdminDeleteRoleViewModel==null)
      hcsAdminDeleteRoleViewModel= new HCSAdminDeleteRoleViewModel(mf.getModelAdmin());
    return hcsAdminDeleteRoleViewModel;
  }

  public DoctorViewModel getHcsDoctorViewModel()
  {
    if (doctorViewModel ==null) doctorViewModel =new DoctorViewModel(mf.getModelDoctor());
    return doctorViewModel;
  }

}
