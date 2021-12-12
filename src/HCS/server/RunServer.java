package HCS.server;

import HCS.DataBase.*;
//import chat.DataBase.MainDAO;
import HCS.server.model.*;
import HCS.server.network.RMIServerImpl;
//import chat.server.network.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args)
        throws RemoteException, AlreadyBoundException
    {
        UserDAO adminDAO = UserDAO.getInstance();
        PatientDAO patientDAO= PatientDAO.getInstance();
        LoginDAO loginDAO = LoginDAO.getInstance();
        BookingDAO bookingDAO= BookingDAO.getInstance();
        PrescriptionDAO prescriptionDAO=PrescriptionDAO.getInstance();
        PrescriptionTypeDAO prescriptionTypeDAO= PrescriptionTypeDAO.getInstance();
       // MainDAO mainDAO=MainDAO.getInstance();
        LoginModelServerImpl serverModelLogin=new LoginModelServerImpl(loginDAO);
        AdminModelServerImpl adminModelServer=new AdminModelServerImpl(adminDAO);
        PatientModelServerImpl patientModelServer=new PatientModelServerImpl(patientDAO,bookingDAO);
        BookingModelServerImpl bookingModelServer=new BookingModelServerImpl(bookingDAO,prescriptionDAO);
        PrescriptionModelServerImpl prescriptionModelServer=new PrescriptionModelServerImpl(prescriptionDAO,prescriptionTypeDAO);

        RMIServerImpl rs = new RMIServerImpl(serverModelLogin,adminModelServer,patientModelServer,bookingModelServer,prescriptionModelServer);
       // RMIServer rs = new RMIServer(new ServerModel(mainDAO));

        rs.startServer();

        /* AdminDAO adminDAO = AdminDAO.getInstance();
        PatientDAO patientDAO= PatientDAO.getInstance();
        LoginDAO loginDAO=LoginDAO.getInstance();
        BookingDAO bookingDAO= BookingDAO.getInstance();
        PrescriptionDAO prescriptionDAO=PrescriptionDAO.getInstance();
       // MainDAO mainDAO=MainDAO.getInstance();


        RMIServerImpl rs = new RMIServerImpl(new ServerModelImpl(adminDAO,patientDAO,loginDAO,bookingDAO,prescriptionDAO));
       // RMIServer rs = new RMIServer(new ServerModel(mainDAO));

        rs.startServer();

        */
    }
}
