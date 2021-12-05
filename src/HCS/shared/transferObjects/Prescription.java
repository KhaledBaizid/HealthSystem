package HCS.shared.transferObjects;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.sql.Date;

public class Prescription implements Serializable
{
  private Date bookingDate;
  private String bookingTime;
  private String prescriptionType;
  private String prescriptionText;
  private String cprNumber;
  private String firstname;
  private String lastname;
  private Date birthday;
  private String sex;
  private String symptoms;

  public Prescription(Date bookingDate, String bookingTime, String prescriptionType, String prescriptionText )
  {
    this.bookingDate=bookingDate;
    this.bookingTime=bookingTime;
    this.prescriptionType=prescriptionType;
    this.prescriptionText=prescriptionText;
  }
  public Prescription(Date bookingDate, String bookingTime,String cprNumber,String firstname, String lastname, Date birthday, String sex,String symptoms, String prescriptionType , String prescriptionText )
  {
    this.bookingDate=bookingDate;
    this.bookingTime=bookingTime;
    this.prescriptionType=prescriptionType;
    this.prescriptionText=prescriptionText;
    this.cprNumber=cprNumber;
    this.firstname=firstname;
    this.lastname=lastname;
    this.birthday=birthday;
    this.sex=sex;
    this.symptoms=symptoms;
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

  public String getSex()
  {
    return sex;
  }

  public String getBookingTime()
  {
    return bookingTime;
  }

  public Date getBookingDate()
  {
    return bookingDate;
  }

  public String getCprNumber()
  {
    return cprNumber;
  }

  public String getPrescriptionText()
  {
    return prescriptionText;
  }

  public String getPrescriptionType()
  {
    return prescriptionType;
  }

  public String getSymptoms()
  {
    return symptoms;
  }
}
