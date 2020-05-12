package net.alfiesmith.multicommand;

import net.alfiesmith.multicommand.listener.CommandListener;
import net.alfiesmith.multicommand.manager.MultiCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiCommandPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        MultiCommandManager manager = new MultiCommandManager();
        manager.loadCommands(getConfig());

        getServer().getPluginManager().registerEvents(new CommandListener(manager), this);
    }
}
