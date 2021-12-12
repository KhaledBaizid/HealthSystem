package HCS.DataBase;

import HCS.shared.transferObjects.Patient;
import HCS.shared.transferObjects.PrescriptionType;

import java.sql.*;
import java.util.ArrayList;

public class PrescriptionTypeDAO implements ManagePrescriptionTypeDAO
{
  private JDBCController jdbcController;
  private static PrescriptionTypeDAO instance;

  private PrescriptionTypeDAO()
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

  public static synchronized PrescriptionTypeDAO getInstance()
  {
    if (instance == null)
    {
      instance = new PrescriptionTypeDAO();
    }
    return instance;
  }

  @Override public void createPrescriptionType(
      PrescriptionType prescriptionType)
  {
    try(Connection connection = jdbcController.getConnection()) {
      System.out.println("PrescriptionTypeDAO");
      PreparedStatement statement = connection.prepareStatement("INSERT INTO prescriptiontype VALUES (?)");
      statement.setString(1, prescriptionType.getTypeName());
      statement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override public ArrayList<String> getPrescriptionsType()
  {
    ArrayList<String> prescriptionTypes=new ArrayList<>();
    try (Connection connection = jdbcController.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM prescriptiontype  ");

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String prescriptionType = resultSet.getString("typeName");

        prescriptionTypes.add(prescriptionType);


      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return prescriptionTypes;
  }
}
