package io.github.lantiastudios.lobby.sql;

import io.github.lantiastudios.lobby.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;
import java.util.List;

public class Item {

    private Material material;
    private String name;
    private int place;
    private String lore;
    private InventoryItemType inventoryItemType;

    public Item(Material material,String name,int place,String lore,InventoryItemType inventoryItemType) {
        this.material = material;
        this.name = name;
        this.place = place;
        this.lore = lore;
        this.inventoryItemType = inventoryItemType;
    }
    public Material getMaterial() {
        return material;
    }
    public String getName() {
        return name;
    }
    public int getPlace() {
        return place;
    }
    public String getLore() {
        return lore;
    }
    public InventoryItemType getInventoryItemType() {
        return inventoryItemType;
    }

    public ItemStack getAsItemStack() {
        return new ItemBuilder(material,1).setName(name).setLore(splitLoreString(lore)).create();
    }

    protected String splitLoreString(String lore) {
        return lore.replace("//","\n");
    }
}
