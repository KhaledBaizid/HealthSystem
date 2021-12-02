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

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
  }


 /* public UserChatViewModel getUserChatViewModel()
  {
    if (userChatViewModel == null) userChatViewModel = new UserChatViewModel(mf.getModel());
    return userChatViewModel;
  }

  public UserLoginViewModel getUserLoginViewModel(){
    if (userloginviewmodel == null)
      userloginviewmodel = new UserLoginViewModel(mf.getModel());
    return userloginviewmodel;
  }*/

  public LoginViewModel getHcsLoginViewModel()
  {
    /*if (hcsLoginViewModel==null)
      hcsLoginViewModel= new HCSLoginViewModel(mf.getModel());
    return hcsLoginViewModel;*/
    if (loginViewModel ==null)
      loginViewModel = new LoginViewModel(mf.getModelLoginInterface());
    return loginViewModel;
  }

  public AdminViewModel getHcsAdminViewModel()
  {
    if (adminViewModel ==null)
      adminViewModel = new AdminViewModel(mf.getModel());
    return adminViewModel;
  }

  public ReceptionViewModel getHcsReceptionistViewModel()
  {
    if (receptionViewModel ==null)
      receptionViewModel = new ReceptionViewModel(
           mf.getModelReceptionInterface());
    return receptionViewModel;
  }

  public HCSAdminDeleteRoleViewModel getHcsAdminDeleteRoleViewModel()
  {
    if (hcsAdminDeleteRoleViewModel==null)
      hcsAdminDeleteRoleViewModel= new HCSAdminDeleteRoleViewModel(mf.getModel());
    return hcsAdminDeleteRoleViewModel;
  }

  public DoctorViewModel getHcsDoctorViewModel()
  {
    if (doctorViewModel ==null) doctorViewModel =new DoctorViewModel(mf.getModelDoctorInterface());
    return doctorViewModel;
  }

}
