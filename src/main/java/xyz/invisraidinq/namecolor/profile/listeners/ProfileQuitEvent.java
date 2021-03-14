package xyz.invisraidinq.namecolor.profile.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.invisraidinq.namecolor.NameColorPlugin;

public class ProfileQuitEvent implements Listener {

    private final NameColorPlugin plugin;

    public ProfileQuitEvent(NameColorPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProfileQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.plugin.getProfileManager().saveProfile(player);
    }
}
