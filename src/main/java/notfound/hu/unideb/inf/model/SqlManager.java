package notfound.hu.unideb.inf.model;
import java.sql.*;
import java.util.logging.Logger;
public class SqlManager {

    private static final Logger log = Logger.getLogger(SqlManager.class.getName());

    public static Connection connect() throws Exception
    {
        String connectionUrl = "jdbc:sqlserver://sfmnotfound.database.windows.net:1433;database=SFM;user=username@sfmnotfound;password=Password1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

                try{
                    Connection connection = DriverManager.getConnection(connectionUrl);
                    log.info("sikerült a csatlakozas");
                    return connection;
                }
                catch (Exception e)
                {
                    log.info("nem sikerült csatlakozni:" + e);
                    e.printStackTrace();
                }
                return null;
    }


}
