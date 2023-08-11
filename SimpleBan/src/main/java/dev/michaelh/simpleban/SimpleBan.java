package dev.michaelh.simpleban;

import dev.michaelh.simpleban.Commands.BanCommand;
import dev.michaelh.simpleban.Commands.UnbanCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleBan extends JavaPlugin {

    @Override
    public void onEnable() {
        // Initialize your plugin here
        saveDefaultConfig(); // Saves the default config.yml if it doesn't exist
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
    }

    @Override
    public void onDisable() {
        // Cleanup tasks if needed
    }
}

