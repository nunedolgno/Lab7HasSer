package Database;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.*;
import java.util.Properties;

public class DatabaseCommunicator {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studs";
    private static final String USER = "s282606";
    private static final String PASS = "pvl571";
    private static Connection connection;
    private static Statement statement;
    private static OrganizationsBase organizations;
    private static SecretBase users;

    private String strSshUser = "s282606";
    private String strSshPassword = "pvl571";
    private String strSshHost = "se.ifmo.ru";
    private int nSshPort = 2222;
    private String strRemoteHost = "pg";
    private int nLocalPort = 3749;
    private int nRemotePort = 5432;

    public void start() throws SQLException {
        try {
        doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:"+nLocalPort+"/studs", USER, PASS);
        statement = connection.createStatement();
        users = new SecretBase(connection);
        organizations = new OrganizationsBase(connection);
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println("Проблемы с подключением к базе данных.");
            e.printStackTrace();
            System.exit(0);
        }
        if (connection != null) {
            System.out.println("Подключение к базе данных прошло успешно.");
        } else {
            System.out.println("Не удалось подключиться к базе данных.");
        }
    }

    public static OrganizationsBase getOrganizations() {
        return organizations;
    }

    public void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort){
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(strSshUser, strSshHost, nSshPort);
            session.setPassword(strSshPassword);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
        }catch (JSchException e){System.out.println("Ошибка в подключение по Ssh");}
    }

    public static SecretBase getUsers() {
        return users;
    }
}