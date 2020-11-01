package net.alfiesmith.multicommand;

import java.util.List;
import lombok.Value;

@Value
public class MultiCommand {

    String label, permission;
    boolean consoleExecute;
    List<String> commands;

}
