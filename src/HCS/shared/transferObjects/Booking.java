package HCS.shared.transferObjects;

import java.io.Serializable;
import java.sql.Date;

public class Booking implements Serializable
{
 private  String cprNumber;
 private Date bookingDate;
 private String bookingTime;
 private String symptoms;
 private String firstname;
 private String lastname;
 private String sex;
 private Date birthday;

 public Booking(Date bookingDate,String bookingTime,String symptoms,String cprNumber)
 {
   this.bookingDate=bookingDate;
   this.bookingTime=bookingTime;
   this.symptoms=symptoms;
   this.cprNumber=cprNumber;

 }
  public Booking(Date bookingDate,String bookingTime,String cprNumber,String firstname,String lastname,Date birthday,String sex,String symptoms)
  {
    this.bookingDate=bookingDate;
    this.bookingTime=bookingTime;
    this.symptoms=symptoms;
    this.cprNumber=cprNumber;
    this.firstname=firstname;
    this.lastname=lastname;
    this.sex=sex;
    this.birthday=birthday;

  }

  public String getCprNumber()
  {
    return cprNumber;
  }

  public Date getBookingDate()
  {
    return bookingDate;
  }

  public String getBookingTime()
  {
    return bookingTime;
  }

  public String getSymptoms()
  {
    return symptoms;
  }

  public String getSex()
  {
    return sex;
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
}
