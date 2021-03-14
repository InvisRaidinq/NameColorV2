package xyz.invisraidinq.namecolor.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CC {

    /**
     * @param text the text to colorize
     * @return A coloured string
     */
    public static String colour(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * @param text The text to output
     */
    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(colour("[NameColorV2] " + text));
    }


}
