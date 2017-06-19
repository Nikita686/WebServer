package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nb on 18.06.17.
 */
public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public static Connection getMysqlConnection(){

        try {

            DriverManager.registerDriver((Driver)Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").                     //db type
                    append("localhost:").                       //host name
                    append("3306/").                            //port
                    append("mydb?").                           //db name
                    append("user=test&").                      //login
                    append("password=test").                   //password
                    append("&useSSL=false").                   //SSL
                    append("&serverTimezone=Europe/Moscow");   //Timezone

            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;

        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){

            e.printStackTrace();
        }
        return null;
    }

    public UsersDataSet getUser(long id) throws DBException {
        try {
            return (new UsersDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public UsersDataSet getUserByLogin(String login) throws DBException{
        try {
            long id = new UsersDAO(connection).getUserId(login);
            return (new UsersDAO(connection).get(id));
        } catch (SQLException e){
            throw new DBException(e);
        }
    }

    public long addUser(String name, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(name, password);
            connection.commit();
            return dao.getUserId(name);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
