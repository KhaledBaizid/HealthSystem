package HCS.server;

import HCS.Persistence.*;
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

        LoginModelServerImpl serverModelLogin=new LoginModelServerImpl(loginDAO);
        UserModelServerImpl adminModelServer=new UserModelServerImpl(adminDAO);
        PatientModelServerImpl patientModelServer=new PatientModelServerImpl(patientDAO,bookingDAO);
        BookingModelServerImpl bookingModelServer=new BookingModelServerImpl(bookingDAO,prescriptionDAO);
        PrescriptionModelServerImpl prescriptionModelServer=new PrescriptionModelServerImpl(prescriptionDAO,prescriptionTypeDAO);

        RMIServerImpl rs = new RMIServerImpl(serverModelLogin,adminModelServer,patientModelServer,bookingModelServer,prescriptionModelServer);


        rs.startServer();


    }
}
