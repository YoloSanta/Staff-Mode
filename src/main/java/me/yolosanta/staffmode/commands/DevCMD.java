package me.yolosanta.staffmode.commands;

import me.yolosanta.staffmode.StaffMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DevCMD implements CommandExecutor {

    public DevCMD() {
        StaffMode.getStaffMode().getCommand("dev").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (StaffMode.getStaffMode().getInStaff().contains(player.getUniqueId())) {
            StaffMode.getStaffMode().getInStaff().remove(player.getUniqueId());
            player.sendMessage("Removed.");
            return true;
        }
        StaffMode.getStaffMode().getInStaff().add(player.getUniqueId());
        player.sendMessage("Added.");
        return true;
    }
}
