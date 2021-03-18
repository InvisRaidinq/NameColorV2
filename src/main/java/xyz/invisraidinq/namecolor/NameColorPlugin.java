package xyz.invisraidinq.namecolor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.invisraidinq.namecolor.namecolor.ColorGUIClickListener;
import xyz.invisraidinq.namecolor.namecolor.commands.ColorGUICommand;
import xyz.invisraidinq.namecolor.namecolor.NameColorManager;
import xyz.invisraidinq.namecolor.profile.ProfileManager;
import xyz.invisraidinq.namecolor.profile.listeners.ProfileJoinEvent;
import xyz.invisraidinq.namecolor.profile.listeners.ProfileQuitEvent;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.ConfigFile;
import xyz.invisraidinq.namecolor.utils.command.CommandRegister;

import java.util.Arrays;

public class NameColorPlugin extends JavaPlugin {

    private ConfigFile nameColorsFile;
    private ProfileManager profileManager;
    private NameColorManager nameColorManager;

    @Override
    public void onEnable() {
        CC.log("Starting plugin");
        long start = System.currentTimeMillis();

        this.nameColorsFile = new ConfigFile(this, "namecolors.yml");

        this.profileManager = new ProfileManager(this);
        this.nameColorManager = new NameColorManager(this, this.nameColorsFile);

        Arrays.asList(
                new ProfileJoinEvent(this),
                new ProfileQuitEvent(this),
                new ColorGUIClickListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        CommandRegister commandRegister = new CommandRegister();
        commandRegister.registerCommand(new ColorGUICommand(this), this, false);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderHook(this);
        }

        CC.log("Plugin enabled in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> this.profileManager.saveProfile(player));
    }

    public ProfileManager getProfileManager() {
        return this.profileManager;
    }

    public NameColorManager getNameColorManager() {
        return this.nameColorManager;
    }
}
