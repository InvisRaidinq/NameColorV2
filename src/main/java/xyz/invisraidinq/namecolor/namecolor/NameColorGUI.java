package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.profile.Profile;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.ItemFactory;

import javax.swing.*;

public class NameColorGUI  {

    private final NameColorPlugin plugin;
    private final Player player;

    public NameColorGUI(NameColorPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void openNameColourMenu() {
        Inventory colorInv = Bukkit.createInventory(null, 54, CC.colour("&dName Colours"));

        for (int x = 0; x < colorInv.getSize(); x++) {
            
        }

        this.plugin.getNameColorManager().getNameColorList().forEach(nameColor -> {
            ItemFactory factory = new ItemFactory(nameColor.getMaterial());
            factory.setDurability(nameColor.getData());
            factory.setName(nameColor.getName());

            if (player.hasPermission(nameColor.getPermission())) {
                factory.setLore(nameColor.getUnlockedLore());
            } else {
                factory.setLore(nameColor.getLockedLore());
            }

            colorInv.setItem(nameColor.getSlot(), factory.build());
        });

        this.player.openInventory(colorInv);
    }

}
