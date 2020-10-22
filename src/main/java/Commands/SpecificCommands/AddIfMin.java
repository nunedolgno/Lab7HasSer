package Commands.SpecificCommands;

import Commands.Command;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Server.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;

public class AddIfMin extends Command implements Serializable {
    private static final long serialVersionUID = 32L;
    @Override
    public void execute(Object o, DatagramChannel channel, SocketAddress socketAddress) throws IOException, SQLException {
        SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
        Object obj = combinedCommand.getObject();
        String arg = combinedCommand.getArg();
        Receiver commandReceiver = new Receiver(channel);
        commandReceiver.add_if_min(arg,obj,socketAddress);
    }
}
