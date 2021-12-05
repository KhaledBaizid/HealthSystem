package HCS.server.model;

//import HCS.shared.transferObjects.Message;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Prescription;
import HCS.shared.transferObjects.User;
//import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface ServerModel extends Subject {
   // boolean loginUser(User user);

   // ArrayList<String> sendActiveUsersToClient();

  //  void sendPublicMessage(Message message);

  //  void disconnect(User userDisconnecting);


    String Login(String username,String password);

    boolean UserExist(String username);
    void CreateUser(String firstname,String lastname, Date birthday,String username,String password,String role);
    ArrayList<User> GetUsers();
    void RemoveUser(String username);

    void createPatient(Patient patient);
    void removePatient(String cprNumber);
    void updatePatient(String cprNumber,Patient patient);
    ArrayList<Patient> GetPatients();
    ArrayList<Patient> GetSpecificPatients(String search);

    void createBooking(Booking booking);
    ArrayList<Booking> GetBookings();
    void removeBooking(Date bookingDate,String bookingTime );
    ArrayList<String> getAvailableTime(Date date);
    ArrayList<Booking> GetPatientBookings(String cprNumber);
    ArrayList<Booking> GetPatientBookingsByDate(Date date);

    boolean isPatientHasABooking(String cprNumber);

    ////
    void createPrescription(Prescription prescription);
    ArrayList<Prescription>  getPrescriptions();

}
