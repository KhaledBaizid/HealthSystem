package HCS.shared.transferObjects;

import java.io.Serializable;
import java.sql.Date;

public class Patient implements Serializable
{
  private String cprNumber;
  private String firstname;
  private String lastname;
  private String sex;
  private Date birthday;
  private String address;
  private String phone;
  private String mail;

 public Patient (String cprNumber,String firstname,String lastname,Date birthday,String sex,String address,String phone,String mail)
 {
   this.cprNumber=cprNumber;
   this.firstname=firstname;
   this.lastname=lastname;
   this.birthday=birthday;
   this.sex=sex;
   this.address=address;
   this.phone=phone;
   this.mail=mail;
 }

  public String getFirstname()
  {
    return firstname;
  }

  public Date getBirthday()
  {
    return birthday;
  }

  public String getLastname()
  {
    return lastname;
  }

  public String getAddress()
  {
    return address;
  }

  public String getCprNumber()
  {
    return cprNumber;
  }

  public String getMail()
  {
    return mail;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getSex()
  {
    return sex;
  }
}
