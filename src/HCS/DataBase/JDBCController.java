package HCS.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCController
{
  private static JDBCController instance;

  private JDBCController()
  {
  }

  public static JDBCController getInstance()
  {
    if (instance == null)
    {
      instance = new JDBCController();
    }
    return instance;
  }

  public Connection getConnection() throws SQLException
  {
   // String url = "jdbc:postgresql://hattie.db.elephantsql.com:5432/postgres?currentSchema=sep";
    String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=sepdba";

    String username = "postgres";
    String password = "dba107882";
    return DriverManager.getConnection(url, username, password);
  }

}

