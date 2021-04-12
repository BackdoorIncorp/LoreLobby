package io.github.lantiastudios.lobby.utilities;


import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private ItemStack is;
    private ItemMeta im;

    public ItemBuilder(Material material, int amount){
        this.is = new ItemStack(material, amount);
        this.im = this.is.getItemMeta();
    }
    public ItemBuilder(Material material, int amount, int subid){
        this.is = new ItemStack(material, amount, (short) subid);
        this.im = this.is.getItemMeta();
    }

    public ItemBuilder setName(String name){
        this.im.setDisplayName(name);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        this.im.addEnchant(enchantment, level, false);
        return this;
    }
    public ItemBuilder addItemflag(ItemFlag... itemFlag){
        this.im.addItemFlags(itemFlag);
        return this;
    }

    public ItemBuilder setLore(String... lore){
        this.im.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLore(List<String> lore){
        this.im.setLore(lore);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.is.setAmount(amount);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        SkullMeta meta = (SkullMeta)this.im;
        meta.setOwner(owner);
        return this;
    }

    public ItemBuilder setLeatherColor(Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta)this.im;
        meta.setColor(color);
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        this.is.setDurability((short)durability);
        return this;
    }
    public ItemStack create(){
        this.is.setItemMeta(this.im);
        return this.is;
    }
}