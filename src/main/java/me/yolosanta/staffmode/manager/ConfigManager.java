package me.yolosanta.staffmode.manager;

import lombok.Getter;
import me.yolosanta.staffmode.StaffMode;
import me.yolosanta.staffmode.utils.StringUtils;

@Getter
public class ConfigManager {

    private String freezeName;
    private String vanishONName;
    private String vanishOFFName;
    private String inventoryViewName;

    public ConfigManager(StaffMode staffMode) {
        freezeName = StringUtils.colorize(staffMode.getConfig().getString("Items.Freeze"));
        vanishONName = StringUtils.colorize(staffMode.getConfig().getString("Items.Vanish-ON"));
        vanishOFFName = StringUtils.colorize(staffMode.getConfig().getString("Items.Vanish-OFF"));
        inventoryViewName = StringUtils.colorize(staffMode.getConfig().getString("Items.Inventory-View"));
    }
}
