package dev.michaelh.simpleban.Commands;


import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /unban <player>");
            return true;
        }

        String targetName = args[0];

        // Perform the unban action using Bukkit's ban system
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
        if (banList.isBanned(targetName)) {
            banList.pardon(targetName);
            sender.sendMessage(targetName + " has been unbanned.");
        } else {
            sender.sendMessage(targetName + " is not banned.");
        }

        return true;
    }
}

