package xyz.invisraidinq.namecolor.namecolor;

import org.bukkit.Material;

import java.util.List;

public class NameColor {

    private final String color;
    private final int slot;
    private final String permission;
    private final Material material;
    private final byte data;
    private final String name;
    private final List<String> lore;

    /**
     * @param color The bukkit color code
     * @param slot The slot in the gui
     * @param permission The permission to access it
     * @param material The material for the GUI
     * @param data The data/durability of the material
     * @param name The name of the material
     * @param lore The lore of the material
     */
    public NameColor(String color, int slot, String permission, String material, int data, String name, List<String> lore) {
        this.color = color;
        this.slot = slot;
        this.permission = permission;
        this.material = Material.valueOf(material.toUpperCase());
        this.data = (byte) data;
        this.name = name;
        this.lore = lore;
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

    public List<String> getLore() {
        return this.lore;
    }

}
