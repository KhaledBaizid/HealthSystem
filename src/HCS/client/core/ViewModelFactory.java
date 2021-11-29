package HCS.client.core;

import HCS.client.ViewModel.*;

public class ViewModelFactory
{
  private ModelFactory mf;
 // private UserChatViewModel userChatViewModel;
  //private UserLoginViewModel userloginviewmodel;

  private HCSLoginViewModel hcsLoginViewModel;
  private HCSAdminViewModel hcsAdminViewModel;
  private HCSReceptionistViewModel hcsReceptionistViewModel;
  private HCSAdminDeleteRoleViewModel hcsAdminDeleteRoleViewModel;
  private HCSDoctorViewModel hcsDoctorViewModel;

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

  public HCSLoginViewModel getHcsLoginViewModel()
  {
    /*if (hcsLoginViewModel==null)
      hcsLoginViewModel= new HCSLoginViewModel(mf.getModel());
    return hcsLoginViewModel;*/
    if (hcsLoginViewModel==null)
      hcsLoginViewModel= new HCSLoginViewModel(mf.getModelLoginInterface());
    return hcsLoginViewModel;
  }

  public HCSAdminViewModel getHcsAdminViewModel()
  {
    if (hcsAdminViewModel==null)
      hcsAdminViewModel= new HCSAdminViewModel(mf.getModel());
    return hcsAdminViewModel;
  }

  public HCSReceptionistViewModel getHcsReceptionistViewModel()
  {
    if (hcsReceptionistViewModel==null)
      hcsReceptionistViewModel= new HCSReceptionistViewModel(
           mf.getModelReceptionInterface());
    return hcsReceptionistViewModel;
  }

  public HCSAdminDeleteRoleViewModel getHcsAdminDeleteRoleViewModel()
  {
    if (hcsAdminDeleteRoleViewModel==null)
      hcsAdminDeleteRoleViewModel= new HCSAdminDeleteRoleViewModel(mf.getModel());
    return hcsAdminDeleteRoleViewModel;
  }

  public HCSDoctorViewModel getHcsDoctorViewModel()
  {
    if (hcsDoctorViewModel==null) hcsDoctorViewModel=new HCSDoctorViewModel(mf.getModelDoctorInterface());
    return hcsDoctorViewModel;
  }

}
