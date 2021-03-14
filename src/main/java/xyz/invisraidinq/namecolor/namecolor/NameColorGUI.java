package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.ItemFactory;

public class NameColorGUI  {

    private final NameColorPlugin plugin;

    public NameColorGUI(NameColorPlugin plugin) {
        this.plugin = plugin;
    }

    public void openNameColourMenu(Player player) {
        Inventory colorInv = Bukkit.createInventory(null, 54, CC.colour("&dName Colours"));

        this.plugin.getNameColorManager().getNameColorList().forEach(nameColor -> {
            //Set items here
        });


        player.openInventory(colorInv);
    }

}
