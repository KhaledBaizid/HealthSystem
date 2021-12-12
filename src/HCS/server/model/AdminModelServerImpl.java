package HCS.server.model;

import HCS.DataBase.ManageUserDAO;
import HCS.shared.transferObjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class AdminModelServerImpl implements AdminModelServer
{
  private PropertyChangeSupport support;
  private ManageUserDAO adminDAO;

  public AdminModelServerImpl(ManageUserDAO adminDAO)
  {
    this.adminDAO=adminDAO;
    support = new PropertyChangeSupport(this);
  }

  @Override
  public void addListener(String eventName, PropertyChangeListener listener) {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);

  }


  @Override public boolean UserExist(String username)
  {
    boolean b;
    b=adminDAO.UserExist(username);
    return b;
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {
    adminDAO.CreateUser(firstname, lastname, birthday, username, password, role);
  }

  @Override public ArrayList<User> GetUsers()
  {
    System.out.println("servermodel");
    ArrayList<User> user1;
    user1 =adminDAO.GetUsers();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetRoles",null, user1);
    return user1;
  }

  @Override public void RemoveUser(String username)
  {
    adminDAO.RemoveUser(username);
  }
}
