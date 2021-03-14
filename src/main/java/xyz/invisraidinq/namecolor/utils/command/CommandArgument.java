package xyz.invisraidinq.namecolor.utils.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import java.util.Arrays;
import java.util.List;

public abstract class CommandArgument {

    private String name;
    private String description;

    private boolean requiresPermission;

    private String[] aliases;

    public CommandArgument(String name){
        this(name, null);
    }

    public CommandArgument(String name, String description, String... aliases){
        this.name = name;
        this.description = description;
        this.aliases = Arrays.copyOf(aliases, aliases.length);
    }

    public String getPermission(PluginCommand command) {
        return requiresPermission ? command.getPermission() == null ? "event.command" + command.getName() : command.getPermission() + ".argument" + name : null;
    }

    public abstract String getUsage(String label);

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getAliases() {
        return this.aliases;
    }
}
