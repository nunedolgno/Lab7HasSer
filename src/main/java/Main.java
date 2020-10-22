import Database.DatabaseCommunicator;
import Server.Communicator;
import Utils.MessageMailSender;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        /*if (args.length != 3) {
            System.out.println("Вы должны были ввести порт, логин и пароль от почты.\nПопробуйте ещё раз!");
            System.exit(1);
        }*/
        new MessageMailSender("hrkfyr231g12f@mail.ru", "_hY3rauEiPR2");
        DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();
        databaseCommunicator.start();
        Communicator communicator = new Communicator();
        communicator.run("5555");
    }
}