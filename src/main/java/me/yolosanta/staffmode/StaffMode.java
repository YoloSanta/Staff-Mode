package me.yolosanta.staffmode;

import lombok.Getter;
import me.yolosanta.staffmode.commands.DevCMD;
import me.yolosanta.staffmode.commands.StaffCommand;
import me.yolosanta.staffmode.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Getter
public class StaffMode extends JavaPlugin {

    @Getter
    public static ConfigManager configManager;
    @Getter
    private static StaffMode staffMode;
    @Getter
    private ArrayList<UUID> inStaff = new ArrayList<>();
    @Getter
    private ArrayList<UUID> vanished = new ArrayList<>();
    @Getter
    private HashMap<UUID, ItemStack[]> items = new HashMap<>();
    @Getter
    private HashMap<UUID, ItemStack[]> armor = new HashMap<>();

    @Override
    public void onEnable() {
        staffMode = this;
        configManager = new ConfigManager(this);

        new StaffCommand();
        new DevCMD();
    }

    public void setupInventory(Player player) {
        vanished.add(player.getUniqueId());
        items.put(player.getUniqueId(), player.getInventory().getContents());
        armor.put(player.getUniqueId(), player.getInventory().getArmorContents());
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, inStaff.contains(player.getUniqueId()) ? StaffCommand.vanish : StaffCommand.unvanish);
        player.updateInventory();
    }

    public void restoreInventory(Player player) {
        vanished.remove(player.getUniqueId());
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setContents(items.get(player.getUniqueId()));
        player.getInventory().setArmorContents(armor.get(player.getUniqueId()));
    }

    public void toggleVanish(final Player player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (vanished.contains(player.getUniqueId())) {
                    for (Player online : getServer().getOnlinePlayers()) {
                        online.hidePlayer(player);
                    }
                } else {
                    vanished.remove(player.getUniqueId());
                }
            }
        }, 0, 20L);
    }
}
