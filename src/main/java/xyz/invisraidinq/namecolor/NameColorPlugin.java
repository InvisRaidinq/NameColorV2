package xyz.invisraidinq.namecolor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.invisraidinq.namecolor.profile.ProfileManager;
import xyz.invisraidinq.namecolor.profile.listeners.ProfileJoinEvent;
import xyz.invisraidinq.namecolor.profile.listeners.ProfileQuitEvent;
import xyz.invisraidinq.namecolor.utils.CC;

import java.util.Arrays;

public class NameColorPlugin extends JavaPlugin {

    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        CC.log("Starting plugin");
        long start = System.currentTimeMillis();

        this.profileManager = new ProfileManager(this);

        Arrays.asList(
                new ProfileJoinEvent(this),
                new ProfileQuitEvent(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        CC.log("Plugin enabled in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> this.profileManager.saveProfile(player));
    }

    public ProfileManager getProfileManager() {
        return this.profileManager;
    }
}
