package HCS.client.core;

import HCS.client.model.*;

public class ModelFactory
{
  private ClientFactory cf;
  HCSModelAdminInterface model;
  HCSModelReceptionInterface modelReceptionInterface;
  HCSModelLoginInterface modelLoginInterface;
  HCSModelDoctorInterface modelDoctorInterface;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public HCSModelAdminInterface getModel()
  {
    if (model == null) model = new ModelAdmin(cf.getClient());
    return model;
  }

  public HCSModelReceptionInterface getModelReceptionInterface()
  {
    if (modelReceptionInterface == null) modelReceptionInterface = new ModelReception(cf.getClientReception());
    return modelReceptionInterface;
  }

  public HCSModelLoginInterface getModelLoginInterface()
  {
    if (modelLoginInterface==null) modelLoginInterface=new ModelLogin(cf.getClientLogin());
        return modelLoginInterface;
  }

  public HCSModelDoctorInterface getModelDoctorInterface()
  {
    if (modelDoctorInterface==null) modelDoctorInterface=new ModelDoctor(cf.getClientDoctor());
    return modelDoctorInterface;
  }


}
