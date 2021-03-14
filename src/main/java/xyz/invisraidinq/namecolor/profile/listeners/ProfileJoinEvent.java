package xyz.invisraidinq.namecolor.profile.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.invisraidinq.namecolor.NameColorPlugin;

public class ProfileJoinEvent implements Listener {

    private final NameColorPlugin plugin;

    public ProfileJoinEvent(NameColorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProfileJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.plugin.getProfileManager().loadProfile(player);
    }
}
