package xyz.invisraidinq.namecolor.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static xyz.invisraidinq.namecolor.utils.CC.colour;

public class ItemBuilder {

    public static ItemStack Builder(Material material, int id, String displayName, String lore) {
        ItemStack item = new ItemStack(material, 1, (short) id);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(colour(displayName));
        itemMeta.setLore(Arrays.asList(colour(lore)));
        item.setItemMeta(itemMeta);
        return item;
    }

}
