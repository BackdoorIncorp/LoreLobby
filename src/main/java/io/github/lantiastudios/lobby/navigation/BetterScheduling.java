package io.github.lantiastudios.lobby.navigation;

import io.github.lantiastudios.lobby.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BetterScheduling {


    class ScheduleFillRemainingSlotsTask implements Runnable {


        private Inventory inventory;

        private ItemStack itemStack;

        public ScheduleFillRemainingSlotsTask(Inventory inventory,ItemStack itemStack) {
            this.inventory = inventory;
            this.itemStack = itemStack;
        }
        @Override
        public void run() {
            for(int i = 0;i > inventory.getSize();i++) {
                ItemStack itemStack = inventory.getItem(i);
                if(itemStack != null && itemStack.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    inventory.setItem(i,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE,i).setName(" ").setLore("§4KUSCHELLSTUBE").create());
                    i++;
                }else {
                    i++;
                }
            }
        }
    }
}
