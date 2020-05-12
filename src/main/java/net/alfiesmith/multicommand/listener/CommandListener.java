package net.alfiesmith.multicommand.listener;

import lombok.RequiredArgsConstructor;
import net.alfiesmith.multicommand.MultiCommand;
import net.alfiesmith.multicommand.manager.MultiCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class CommandListener implements Listener {

    private final MultiCommandManager commandManager;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        MultiCommand command = commandManager.getCommand(event.getMessage().substring(1)).orElse(null);
        if (command != null && event.getPlayer().hasPermission(command.getPermission())) {
            event.setCancelled(true);
            CommandSender dispatcher = command.isConsoleExecute() ? Bukkit.getConsoleSender() : event.getPlayer();
            for (String run : command.getCommands()) {
                Bukkit.dispatchCommand(dispatcher, run.replace("%player%", event.getPlayer().getName()));
            }
        }
    }
}
