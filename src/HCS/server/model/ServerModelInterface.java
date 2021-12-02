package HCS.server.model;

//import HCS.shared.transferObjects.Message;
import HCS.shared.transferObjects.Booking;
import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;
//import HCS.shared.transferObjects.User;
import HCS.shared.utility.Subject;

import java.sql.Date;
import java.util.ArrayList;

public interface ServerModelInterface extends Subject {
   // boolean loginUser(User user);

   // ArrayList<String> sendActiveUsersToClient();

  //  void sendPublicMessage(Message message);

  //  void disconnect(User userDisconnecting);


    String HCSLogin(String username,String password);
    boolean roleExist(String username);
    void HCSCreateRole(String firstname,String lastname, Date birthday,String username,String password,String role);
    ArrayList<Role> HCSGetRoles();
    void HCSRemoveRole(String username);

    void createPatient(Patient patient);
    ArrayList<Patient> HCSGetPatients();
    ArrayList<Patient> HCSGetSpecificPatients(String search);
    void createBooking(Booking booking);
    ArrayList<Booking> HCSGetBookings();
    void removeBooking(Date bookingDate,String bookingTime );
    ArrayList<String> getTimeAvailable(Date date);
}
