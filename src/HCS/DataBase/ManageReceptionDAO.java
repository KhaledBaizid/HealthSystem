package HCS.DataBase;

import HCS.shared.transferObjects.Role;

import java.util.ArrayList;

public interface ManageReceptionDAO
{
  void createPatient(String cprnumber ,String firstname,String Lastname);
  ArrayList<Role> HCSGetRoles();
}
