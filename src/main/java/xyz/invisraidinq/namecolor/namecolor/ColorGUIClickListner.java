package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.profile.Profile;
import xyz.invisraidinq.namecolor.utils.CC;

public class ColorGUIClickListner implements Listener {

    private final NameColorPlugin plugin;

    public ColorGUIClickListner(NameColorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (event.getInventory() == null) return;
        if (event.getClickedInventory() == null) return;
        if (!event.getInventory().getName().equalsIgnoreCase(CC.colour("&dName Colours"))) return;
        Player player = (Player) event.getWhoClicked();
        String clickedItemName = event.getCurrentItem().getItemMeta().getDisplayName();

        this.plugin.getNameColorManager().getNameColorList().forEach(nameColor -> { //Probably not too efficient to loop through the list, but it's just for learning
            if (clickedItemName.equalsIgnoreCase(CC.colour(nameColor.getName()))) {
                if (player.hasPermission(nameColor.getPermission())) {
                    Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);
                    profile.setNameColor(nameColor.getColor());
                    player.sendMessage(CC.colour("&aYou have updated your name colour to " + profile.getNameColor() + "this"));
                    player.closeInventory();
                } else {
                    player.sendMessage(CC.colour("&cYou do not have access to this! Purchase it on the server store!"));
                }
            }
        });
    }
}
