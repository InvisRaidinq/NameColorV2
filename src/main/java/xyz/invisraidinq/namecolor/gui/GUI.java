package xyz.invisraidinq.namecolor.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static xyz.invisraidinq.namecolor.utils.CC.colour;

public class GUI {

    public static void BuildGUI(Player player) {
        Inventory GUI = Bukkit.createInventory(null, 54, colour("&cSome name"));



    }

}
