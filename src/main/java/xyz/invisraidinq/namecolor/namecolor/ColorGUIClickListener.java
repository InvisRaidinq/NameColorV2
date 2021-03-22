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
        if (!event.getInventory().getName().equalsIgnoreCase(CC.colour(this.plugin.getSettingsFile().getString("TITLE")))) return;
        event.setCancelled(true);
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        Player player = (Player) event.getWhoClicked();
        String clickedItemName = event.getCurrentItem().getItemMeta().getDisplayName();
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);

        for (NameColor nameColor : this.plugin.getNameColorManager().getNameColorList()) {
            if (clickedItemName.equalsIgnoreCase(CC.colour(nameColor.getNameWithColor()))) {
                if (player.hasPermission(nameColor.getPermission())) {
                    profile.setNameColor(nameColor.getColor());
                    player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.CHANGE-COLOR")
                            .replace("%colorname%", CC.colour(nameColor.getNameWithColor().toLowerCase()))));
                    player.closeInventory();
                } else {
                    player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
                }
            }
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&d&oItalic"))) {
            if (player.hasPermission("namecolor.format.italic")) {
                profile.toggleItalic();
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.ITALICS")));
            } else {
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
            }
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&d&lBold"))) {
            if (player.hasPermission("namecolor.format.bold")) {
                profile.toggleBold();
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.BOLD")));
            } else {
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
            }
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&d&nUnderlined"))) {
            if (player.hasPermission("namecolor.format.underlined")) {
                profile.toggleUnderlined();
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.UNDERLINED")));
            } else {
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
            }
        }

        if (clickedItemName.equalsIgnoreCase(CC.colour("&cReset your name color"))) {
            profile.resetNameColor();
            player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.RESET-COLOR")));
        }
    }
}
