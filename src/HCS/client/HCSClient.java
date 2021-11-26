package HCS.client;

import HCS.client.core.ClientFactory;
import HCS.client.core.ModelFactory;
import HCS.client.core.ViewHandler;
import HCS.client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class HCSClient extends Application
{


  @Override public void start(Stage primaryStage) throws Exception
  {

    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory, primaryStage);

    viewHandler.start();
  }
}
