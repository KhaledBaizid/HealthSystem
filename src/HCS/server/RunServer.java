package HCS.server;

import HCS.DataBase.*;
//import chat.DataBase.MainDAO;
import HCS.server.network.RMIServerImpl;
import HCS.server.model.ServerModelImpl;
//import chat.server.network.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args)
        throws RemoteException, AlreadyBoundException
    {
        AdminDAO adminDAO = AdminDAO.getInstance();
        PatientDAO patientDAO= PatientDAO.getInstance();
        LoginDAO loginDAO=LoginDAO.getInstance();
        BookingDAO bookingDAO= BookingDAO.getInstance();
        PrescriptionDAO prescriptionDAO=PrescriptionDAO.getInstance();
       // MainDAO mainDAO=MainDAO.getInstance();

        RMIServerImpl rs = new RMIServerImpl(new ServerModelImpl(adminDAO,patientDAO,loginDAO,bookingDAO,prescriptionDAO));
       // RMIServer rs = new RMIServer(new ServerModel(mainDAO));

        rs.startServer();
    }
}
