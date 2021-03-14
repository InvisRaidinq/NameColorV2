package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.invisraidinq.namecolor.NameColorPlugin;
import xyz.invisraidinq.namecolor.utils.CC;
import xyz.invisraidinq.namecolor.utils.command.ExecutableCommand;

public class NameColorCommand extends ExecutableCommand {

    private final NameColorPlugin plugin;

    public NameColorCommand(NameColorPlugin plugin) {
        super("namecolor", "Open the namecolor GUI", "nc");

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
        new NameColorGUI(this.plugin).openNameColourMenu(player);
        return true;
    }
}
