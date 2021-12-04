package HCS.shared.transferObjects;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable
{
  private String firstname;
  private String lastname;
  private Date birthday;
  private String username;
  private String password;
  private String role;

  public User(String firstname,String lastname,Date birthday,String username,String password,String role)
  {
    this.firstname=firstname;
    this.lastname=lastname;
    this.birthday=birthday;
    this.username=username;
    this.password=password;
    this.role=role;
  }

  public String getLastname()
  {
    return lastname;
  }

  public Date getBirthday()
  {
    return birthday;
  }

  public String getFirstname()
  {
    return firstname;
  }

  public String getPassword()
  {
    return password;
  }

  public String getRole()
  {
    return role;
  }

  public String getUsername()
  {
    return username;
  }

  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }

  public void setBirthday(Date birthday)
  {
    this.birthday = birthday;
  }

  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}

