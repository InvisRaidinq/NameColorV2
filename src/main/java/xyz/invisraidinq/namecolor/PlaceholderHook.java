package xyz.invisraidinq.namecolor;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.invisraidinq.namecolor.profile.Profile;
import xyz.invisraidinq.namecolor.utils.CC;

public class PlaceholderHook extends PlaceholderExpansion {

    private final NameColorPlugin plugin;

    public PlaceholderHook(NameColorPlugin plugin) {
        this.plugin = plugin;

        this.register();
    }

    @Override
    public String getIdentifier() {
        return "namecolorv2";
    }

    @Override
    public String getAuthor() {
        return "InvisRaidinq and NeedVoid";
    }

    @Override
    public String getVersion() {
        return "0.0.1-SNAPSHOT";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);
        if (identifier.equalsIgnoreCase("custom_name")) {
            return CC.colour(profile.getCustomNameAttributes());
        }

        if (identifier.equalsIgnoreCase("custom_display_name")) {
            return CC.colour(profile.getCustomNameAttributes() + player.getName());
        }

        return "[NameColorV2] Invalid Placeholder Request";
    }
}
