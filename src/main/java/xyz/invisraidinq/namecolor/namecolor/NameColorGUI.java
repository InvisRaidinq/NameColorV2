package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.ItemFactory;

public class NameColorGUI  {

    private final NameColorPlugin plugin;
    private final Player player;
    private final ItemStack glassFiller = new ItemFactory(Material.STAINED_GLASS_PANE).setDurability((short) 15).setName(" ").build();

    public NameColorGUI(NameColorPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void openNameColourMenu() {
        Inventory colorInv = Bukkit.createInventory(null, 18, (CC.colour(this.plugin.getSettingsFile().getString("TITLE"))));

        for (int x = 0; x < colorInv.getSize(); x++) {
            colorInv.setItem(x, this.glassFiller); //Sets this in every single slot as a filler
        }

        this.plugin.getNameColorManager().getNameColorList().forEach(nameColor -> {
            ItemFactory factory = new ItemFactory(nameColor.getMaterial());
            factory.setDurability(nameColor.getData());
            factory.setName(nameColor.getNameWithColor());

            if (player.hasPermission(nameColor.getPermission())) {
                factory.setLore(nameColor.getUnlockedLore());
            } else {
                factory.setLore(nameColor.getLockedLore());
            }

            colorInv.setItem(nameColor.getSlot(), factory.build());
        });

        //Italic item
        colorInv.setItem(14, new ItemFactory(Material.BEDROCK).setName("&d&oItalic").setLore(" ", "&7&o(( &f&oClick &7&oto toggle &f&oitalics &7&o))").build());

        //Bold item
        colorInv.setItem(15, new ItemFactory(Material.BEDROCK).setName("&d&lBold").setLore(" ", "&7&o(( &f&oClick &7&oto toggle &f&lbold &7&o))").build());

        //Underlined item
        colorInv.setItem(16, new ItemFactory(Material.BEDROCK).setName("&d&nUnderlined").setLore(" ", "&7&o(( &f&oClick &7&oto toggle &f&nunderline &7&o))").build());

        //Name color reset item
        colorInv.setItem(17, new ItemFactory(Material.REDSTONE_BLOCK).setName("&cReset your name color").build());

        this.player.openInventory(colorInv);
    }

}
