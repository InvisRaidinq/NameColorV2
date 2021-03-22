package xyz.invisraidinq.namecolor.namecolor.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.namecolor.NameColor;
import xyz.invisraidinq.namecolor.profile.Profile;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.command.ExecutableCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NameColorCommand extends ExecutableCommand {

    private final NameColorPlugin plugin;
    private final Map<UUID, String> colorChangedMap = new HashMap<>(); //Stored in a map because I have no idea how else to do it in a lambda expression

    public NameColorCommand(NameColorPlugin plugin) {
        super("namecolor", "Edit your name color with a command", "ncolor", "color");

        this.plugin = plugin;

        this.setUsage(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(CC.colour("&cThis is a player command only!"));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(CC.colour(this.plugin.getSettingsFile().getString("USAGE")).replace("%label%", label));
            return true;
        }

        Player player = (Player) sender;
        List<NameColor> colorList = this.plugin.getNameColorManager().getNameColorList();
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < args.length; x++) {
            builder.append(args[x]).append(" ");
        }

        this.colorChangedMap.remove(player.getUniqueId()); //Just incase they're already in the map
        this.colorChangedMap.put(player.getUniqueId(), profile.getNameColor());

        for (NameColor color : this.plugin.getNameColorManager().getNameColorList()) {
            String colorToTest = color.getName() + " ";
            if (colorToTest.equalsIgnoreCase(builder.toString().toLowerCase())) {
                if (player.hasPermission(color.getPermission())) {
                    profile.setNameColor(color.getColor());
                    player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.CHANGE-COLOR")
                            .replace("%colorname%", CC.colour(color.getNameWithColor().toLowerCase()))));
                    return true;
                } else {
                    player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
                    return true;
                }
            }
        }

        if (args[0].equalsIgnoreCase("italic")) {
            if (player.hasPermission("namecolor.format.italic")) {
                profile.toggleItalic();
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.ITALICS")));
                return true;
            } else {
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("bold")) {
            if (player.hasPermission("namecolor.format.bold")) {
                profile.toggleBold();
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.BOLD")));
                return true;
            } else {
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("underlined")) {
            if (player.hasPermission("namecolor.format.underlined")) {
                profile.toggleUnderlined();
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.UNDERLINED")));
                return true;
            } else {
                player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NO-PERMISSION-PURCHASE")));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("reset")) {
            profile.resetNameColor();
            player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.RESET-COLOR")));
            return true;
        }

        if (!args[0].equalsIgnoreCase(this.colorChangedMap.get(player.getUniqueId()))) {
            player.sendMessage(CC.colour(this.plugin.getLangFile().getString("MESSAGES.NAMECOLOR.INVALID-COLOR")));
        }

        return true;
    }

}