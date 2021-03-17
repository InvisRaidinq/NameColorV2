package xyz.invisraidinq.namecolor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.invisraidinq.namecolor.namecolor.ColorGUIClickListener;
import xyz.invisraidinq.namecolor.namecolor.SettingsManager;
import xyz.invisraidinq.namecolor.namecolor.commands.ColorGUICommand;
import xyz.invisraidinq.namecolor.namecolor.NameColorManager;
import xyz.invisraidinq.namecolor.profile.ProfileManager;
import xyz.invisraidinq.namecolor.profile.listeners.ProfileJoinEvent;
import xyz.invisraidinq.namecolor.profile.listeners.ProfileQuitEvent;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.ConfigFile;
import xyz.invisraidinq.namecolor.utils.MessagesManager;
import xyz.invisraidinq.namecolor.utils.command.CommandRegister;

import java.util.Arrays;

public class NameColorPlugin extends JavaPlugin {

    private ConfigFile nameColorsFile;
    private ConfigFile langFile;
    private ConfigFile settingsFile;
    private ProfileManager profileManager;
    private NameColorManager nameColorManager;
    private MessagesManager messagesManager;
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        CC.log("Starting plugin");
        long start = System.currentTimeMillis();

        this.nameColorsFile = new ConfigFile(this, "namecolors.yml");
        this.langFile = new ConfigFile(this, "lang.yml");
        this.settingsFile = new ConfigFile(this, "settings.yml");

        this.profileManager = new ProfileManager(this);
        this.nameColorManager = new NameColorManager(this, this.nameColorsFile);
        this.messagesManager = new MessagesManager(this, this.langFile);
        this.settingsManager = new SettingsManager(this, this.settingsFile);

        Arrays.asList(
                new ProfileJoinEvent(this),
                new ProfileQuitEvent(this),
                new ColorGUIClickListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        CommandRegister commandRegister = new CommandRegister();
        commandRegister.registerCommand(new ColorGUICommand(this), this, false);

        CC.log("Plugin enabled in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> this.profileManager.saveProfile(player));
    }

    public ConfigFile getNameColorsFile() {
        return this.nameColorsFile;
    }

    public ConfigFile getMessages() {
        return this.langFile;
    }

    public ConfigFile getSettingsFile() {
        return this.settingsFile;
    }

    public ProfileManager getProfileManager() {
        return this.profileManager;
    }

    public NameColorManager getNameColorManager() {
        return this.nameColorManager;
    }

    public MessagesManager getMessagesManager() {
        return this.messagesManager;
    }

    public SettingsManager getSettingsManager() {
        return this.settingsManager;
    }
}
