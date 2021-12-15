package HCS.server.model;

import HCS.Persistence.ManageUserDAO;
import HCS.shared.transferObjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.ArrayList;

public class UserModelServerImpl implements UserModelServer
{
  private PropertyChangeSupport support;
  private ManageUserDAO userDAO;

  public UserModelServerImpl(ManageUserDAO userDAO)
  {
    this.userDAO=userDAO;
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
    b=userDAO.UserExist(username);
    return b;
  }

  @Override public void CreateUser(String firstname, String lastname,
      Date birthday, String username, String password, String role)
  {
    userDAO.CreateUser(firstname, lastname, birthday, username, password, role);
  }

  @Override public ArrayList<User> GetUsers()
  {
    System.out.println("servermodel");
    ArrayList<User> user1;
    user1 =userDAO.GetUsers();
    //support.firePropertyChange("HCSGetRoles",null,userDAO.HCSGetRoles());
    // return userDAO.HCSGetRoles();
    support.firePropertyChange("HCSGetRoles",null, user1);
    return user1;
  }

  @Override public void RemoveUser(String username)
  {
    userDAO.RemoveUser(username);
  }

  @Override public void updateUser(String username, User user)
  {
    userDAO.updateUser(username, user);
  }
}
