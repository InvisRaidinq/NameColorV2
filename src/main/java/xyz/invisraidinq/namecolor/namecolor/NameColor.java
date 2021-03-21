package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.Material;
import xyz.invisraidinq.namecolor.utils.CC;

import java.util.List;

public class NameColor {

    private final String color;
    private final int slot;
    private final String permission;
    private final Material material;
    private final byte data;
    private final String name;
    private final String nameWithColor;
    private final List<String> unlockedLore;
    private final List<String> lockedLore;

    /**
     * @param color The bukkit color code
     * @param slot The slot in the gui
     * @param permission The permission to access it
     * @param material The material for the GUI
     * @param data The data/durability of the material
     * @param name The name of the material
     * @param unlockedLore The unlocked lore of the material
     * @param lockedLore The locked lore of the plugin
     */
    public NameColor(String color, int slot, String permission, String material, int data, String name, List<String> unlockedLore, List<String> lockedLore) {
        this.color = color;
        this.slot = slot;
        this.permission = permission;
        this.material = Material.valueOf(material.toUpperCase());
        this.data = (byte) data;
        this.name = name;
        this.nameWithColor = this.color + this.name;
        this.unlockedLore = unlockedLore;
        this.lockedLore = lockedLore;
    }

    public String getColor() {
        return this.color;
    }

    public int getSlot() {
        return this.slot;
    }

    public String getPermission() {
        return this.permission;
    }

    public Material getMaterial() {
        return this.material;
    }

    public byte getData() {
        return this.data;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithColor() {
        return this.nameWithColor;
    }

    public List<String> getUnlockedLore() {
        return this.unlockedLore;
    }

    public List<String> getLockedLore() {
        return this.lockedLore;
    }

}
