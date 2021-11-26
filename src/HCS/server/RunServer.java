package HCS.server;

import HCS.DataBase.LoginDAO;
//import chat.DataBase.MainDAO;
import HCS.DataBase.ReceptionDAO;
import HCS.DataBase.UserDAOImpl;
import HCS.server.network.RMIServer;
import HCS.server.model.ServerModel;
//import chat.server.network.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args)
        throws RemoteException, AlreadyBoundException
    {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        ReceptionDAO receptionDAO=ReceptionDAO.getInstance();
        LoginDAO loginDAO=LoginDAO.getInstance();
       // MainDAO mainDAO=MainDAO.getInstance();

        RMIServer rs = new RMIServer(new ServerModel(userDAO,receptionDAO,loginDAO));
       // RMIServer rs = new RMIServer(new ServerModel(mainDAO));

        rs.startServer();
    }
}
