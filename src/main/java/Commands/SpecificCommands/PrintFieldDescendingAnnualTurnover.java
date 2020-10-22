package Commands.SpecificCommands;

import Commands.Command;
import Server.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class PrintFieldDescendingAnnualTurnover extends Command implements Serializable {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object o, DatagramChannel channel, SocketAddress socketAddress) throws IOException {
        Receiver commandReceiver = new Receiver(channel);
        commandReceiver.print_field_descending_annual_turnover((String)o,socketAddress);
    }
}
