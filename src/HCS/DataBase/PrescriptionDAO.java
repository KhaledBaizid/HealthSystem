package HCS.DataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PrescriptionDAO implements ManagePrescriptionDAO
{
  private JDBCController jdbcController;
  private static PrescriptionDAO instance;

  private PrescriptionDAO()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    jdbcController = JDBCController.getInstance();

  }

  public static synchronized PrescriptionDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PrescriptionDAO();
    }
    return instance;
  }
}
