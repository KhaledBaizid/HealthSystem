package HCS.DataBase;

import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.Role;

import java.util.ArrayList;

public interface ManageReceptionDAO
{
  void createPatient(Patient patient);
  ArrayList<Role> HCSGetRoles();
  ArrayList<Patient> HCSGetPatients();
}
