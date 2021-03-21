package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.profile.Profile;
import xyz.invisraidinq.namecolor.utils.CC;

public class ColorGUIClickListener implements Listener {

    private final NameColorPlugin plugin;

    public ColorGUIClickListener(NameColorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (event.getInventory() == null) return;
        if (event.getClickedInventory() == null) return;
        if (!event.getInventory().getName().equalsIgnoreCase(CC.colour("&dName Colours"))) return;
        event.setCancelled(true);
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        Player player = (Player) event.getWhoClicked();
        String clickedItemName = event.getCurrentItem().getItemMeta().getDisplayName();
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);

        this.plugin.getNameColorManager().getNameColorList().forEach(nameColor -> { //Probably not too efficient to loop through the list, but it's just for learning
            if (clickedItemName.equalsIgnoreCase(CC.colour(nameColor.getNameWithColor()))) {
                if (player.hasPermission(nameColor.getPermission())) {
                    profile.setNameColor(nameColor.getColor());
                    player.sendMessage(CC.colour("&aYou have updated your name colour to " + profile.getNameColor() + "this"));
                    player.closeInventory();
                } else {
                    player.sendMessage(CC.colour(this.plugin.getSettingsFile().getString("NO-PERMISSION-PURCHASE")));
                }
            }
        });

        if (clickedItemName.equalsIgnoreCase(CC.colour("&d&oItalic"))) {
            profile.toggleItalic();
            player.sendMessage(CC.colour("&aYou have toggled your &a&oitalics"));
            player.closeInventory();
            return;
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&d&lBold"))) {
            profile.toggleBold();
            player.sendMessage(CC.colour("&aYou have toggled your &a&lbold"));
            player.closeInventory();
            return;
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&d&nUnderlined"))) {
            profile.toggleUnderlined();
            player.sendMessage(CC.colour("&aYou have toggled your &a&nunderlined"));
            player.closeInventory();
            return;
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&cReset your name color"))) {
            profile.resetNameColor();
            player.sendMessage(CC.colour("&cYou have reset your name color"));
            player.closeInventory();
        }
    }
}
