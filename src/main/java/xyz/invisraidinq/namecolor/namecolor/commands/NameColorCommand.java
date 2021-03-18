package xyz.invisraidinq.namecolor.namecolor.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.namecolor.NameColor;
import xyz.invisraidinq.namecolor.profile.Profile;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.command.ExecutableCommand;

import java.util.List;

public class NameColorCommand extends ExecutableCommand {

    private final NameColorPlugin plugin;

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
            sender.sendMessage(CC.colour("&cUsage: /" + label + " <color>"));
            return true;
        }

        Player player = (Player) sender;
        List<NameColor> colorList = this.plugin.getNameColorManager().getNameColorList();
        Profile profile = this.plugin.getProfileManager().getProfileByPlayer(player);
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < args.length; x++) {
            builder.append(args[x]).append(" ");
        }

        Bukkit.broadcastMessage(builder.toString().toLowerCase());

        colorList.forEach(color -> {
            Bukkit.broadcastMessage(color.getName() + " and stripped case is " + color.getName().toLowerCase());
            String colorToTest = color.getName() + " ";
            if (colorToTest.equalsIgnoreCase(builder.toString().toLowerCase())) {
                Bukkit.broadcastMessage("Detected strip for " + color.getName());
                if (player.hasPermission(color.getPermission())) {
                    profile.setNameColor(color.getColor());
                    player.sendMessage(CC.colour("&aYou have set your name color to " + color.getColor() + "this"));
                } else {
                    player.sendMessage(CC.colour("&cNo Permission"));
                }
                return;
            };
        });

        player.sendMessage(CC.colour("&cThat color doesn't exist"));
        return true;
    }
}
