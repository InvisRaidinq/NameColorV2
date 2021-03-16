package xyz.invisraidinq.namecolor.namecolor.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.namecolor.NameColorGUI;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.command.ExecutableCommand;

public class ColorGUICommand extends ExecutableCommand {

    private final NameColorPlugin plugin;

    public ColorGUICommand(NameColorPlugin plugin) {
        super("colorgui", "Open the namecolor GUI", "colors");

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(CC.colour("&cThis is a player command only"));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(CC.colour("&aOpening the NameColor GUI"));
        new NameColorGUI(this.plugin, player).openNameColourMenu();
        return true;
    }
}
