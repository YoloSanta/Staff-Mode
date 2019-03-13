package me.yolosanta.staffmode.commands;

import me.yolosanta.staffmode.StaffMode;
import me.yolosanta.staffmode.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class StaffCommand implements CommandExecutor {

    public static ItemStack freeze = new ItemBuilder(Material.INK_SACK).setDurability((short) 8).setName(StaffMode.getConfigManager().getFreezeName()).toItemStack();
    public static ItemStack vanish = new ItemBuilder(Material.INK_SACK).setDurability((short) 8).setName(StaffMode.getConfigManager().getVanishONName()).toItemStack();
    public static ItemStack unvanish = new ItemBuilder(Material.INK_SACK).setDurability((short) 8).setName(StaffMode.getConfigManager().getVanishOFFName()).toItemStack();
    public static ItemStack inventory = new ItemBuilder(Material.INK_SACK).setDurability((short) 8).setName(StaffMode.getConfigManager().getInventoryViewName()).toItemStack();

    public StaffCommand() {
        StaffMode.getStaffMode().getCommand("staff").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        if (StaffMode.getStaffMode().getInStaff().contains(uuid)) {
            StaffMode.getStaffMode().getInStaff().remove(uuid);
            StaffMode.getStaffMode().restoreInventory(player);
            return true;
        } else {
            StaffMode.getStaffMode().getInStaff().add(uuid);
            StaffMode.getStaffMode().setupInventory(player);
        }

        return true;
    }
}
