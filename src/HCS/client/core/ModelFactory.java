package HCS.client.core;

import HCS.client.model.*;

public class ModelFactory
{
  private ClientFactory cf;
  AdminModel model;
  ReceptionModel modelReceptionInterface;
  LoginModel modelLoginInterface;
  DoctorModel modelDoctorInterface;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public AdminModel getModel()
  {
    if (model == null) model = new AdminModelImpl(cf.getClient());
    return model;
  }

  public ReceptionModel getModelReceptionInterface()
  {
    if (modelReceptionInterface == null) modelReceptionInterface = new ReceptionModelImpl(cf.getClientReception());
    return modelReceptionInterface;
  }

  public LoginModel getModelLoginInterface()
  {
    if (modelLoginInterface==null) modelLoginInterface=new LoginModelImpl(cf.getClientLogin());
        return modelLoginInterface;
  }

  public DoctorModel getModelDoctorInterface()
  {
    if (modelDoctorInterface==null) modelDoctorInterface=new DoctorModelImpl(cf.getClientDoctor());
    return modelDoctorInterface;
  }


}
