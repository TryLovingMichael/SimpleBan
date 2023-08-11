package dev.michaelh.simpleban.Commands;


import dev.michaelh.simpleban.SimpleBan;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Date;


public class BanCommand implements CommandExecutor {

    private final SimpleBan plugin;

    public BanCommand(SimpleBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /ban <player> <ban message>");
            return true;
        }

        String targetName = args[0];
        Player targetPlayer = Bukkit.getPlayer(targetName);

        if (targetPlayer == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        FileConfiguration config = plugin.getConfig();
        String banPrefix = config.getString("banPrefix", "[Ban]");
        String banMessage = String.join(" ", args).replaceFirst(targetName + " ", "");

        long banDuration = config.getLong("banDuration", -1);


        // Perform the ban action using Bukkit's ban system
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
        Date expiration = (banDuration == -1) ? null : new Date(System.currentTimeMillis() + (banDuration * 1000));
        banList.addBan(targetPlayer.getName(), banMessage, expiration, sender.getName());
        targetPlayer.kickPlayer(banPrefix + " " + banMessage);

        sender.sendMessage(targetName + " has been banned with the message: " + banMessage);

        return true;
    }
}
