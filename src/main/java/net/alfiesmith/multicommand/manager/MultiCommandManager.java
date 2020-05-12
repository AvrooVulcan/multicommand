package net.alfiesmith.multicommand.manager;

import net.alfiesmith.multicommand.MultiCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class MultiCommandManager {

    private final Map<String, MultiCommand> registeredCommands;
    private final Map<String, String> registeredAliases;

    public MultiCommandManager() {
        this.registeredCommands = new HashMap<>();
        this.registeredAliases = new HashMap<>();
    }

    public Optional<MultiCommand> getCommand(String label) {
        Objects.requireNonNull(label, "Label cannot be null");
        MultiCommand command = registeredCommands.get(label);
        if (command == null) {
            command = registeredCommands.get(registeredAliases.get(label));
        }
        return Optional.ofNullable(command);
    }

    public boolean registerCommand(String label, String permission, boolean console, List<String> commands, List<String> aliases) {
        if (label == null || label.isEmpty() || commands.isEmpty()) {
            return false;
        }

        MultiCommand command = new MultiCommand(label, permission, console, commands);
        registeredCommands.put(label, command);
        aliases.forEach(alias -> registeredAliases.put(alias, label));

        return true;
    }

    public void loadCommands(FileConfiguration configuration) {
        for (String label : configuration.getKeys(false)) {
            String path = label + ".";

            String permission = configuration.getString(path + "permission");
            boolean console = configuration.getBoolean(path + "console");
            List<String> commands = configuration.getStringList(path + "commands");
            List<String> aliases = configuration.getStringList(path + "aliases");

            if (registerCommand(label, permission, console, commands, aliases)) {
                Bukkit.getLogger().info("Registered command " + label);
            } else {
                Bukkit.getLogger().severe("Failed to register command " + label);
            }
        }
    }
}
