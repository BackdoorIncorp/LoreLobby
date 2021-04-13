package io.github.lantiastudios.lobby.navigation;

import io.github.lantiastudios.lobby.LobbySystem;
import io.github.lantiastudios.lobby.navigation.exceptions.ItemListHasntAnyContentsException;
import io.github.lantiastudios.lobby.sql.InventoryItemType;
import io.github.lantiastudios.lobby.sql.Item;
import io.github.lantiastudios.lobby.sql.ItemAdapter;
import io.github.lantiastudios.lobby.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LobbyInventories {


    public final void setJoinNavigator(Player player) {
        Inventory inventory = player.getInventory();
        inventory.setItem(4,new ItemBuilder(Material.MUSIC_DISC_11,1).setName("§4Navigation").setLore("Finden sie hier alle unsere Spielmodis").create());
        inventory.setItem(2,new ItemBuilder(Material.BREWING_STAND,1).setName("§3Spieler Verstecken").setLore("§8Stellen sie ihre Preferenzen ein welche Spieler sie sehen wollen!").create());
        inventory.setItem(8,new ItemBuilder(Material.PLAYER_HEAD,1).setSkullOwner(player.getDisplayName()).setName("§2Profil").setLore("§8Stelle ein was für Features du im LobbySystem verwenden willst!").create());
    }

    public final void openNavigation(Player player) {
        List<Item> items = LobbySystem.getLobbySystemInstance().getItemAdapter().getItemByInventoryType(InventoryItemType.NAVIGATION);
        Inventory inventory = Bukkit.createInventory(null,44,"§4Navigator");
        new BetterScheduling.ScheduleFillRemainingSlotsTask(inventory,new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        items.stream().forEach(item -> {
            if(!items.isEmpty()) {
                inventory.setItem(item.getPlace(),item.getAsItemStack());
            }else {
                try {
                    throw new ItemListHasntAnyContentsException("The List is empty please first add contents via /addnewitem command");
                } catch (ItemListHasntAnyContentsException e) {
                    e.printStackTrace();
                }
            }
        });
        player.openInventory(inventory);
    }
    public final void openPlayerHiderInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING,"§4Spieler Verstecken");
        inventory.setItem(0,new ItemBuilder(Material.GREEN_DYE,1).setName("§2Alle Spieler").setLore("§8Dadurch sind alle Spieler wieder vollständig sichtbar für dich").create());
        inventory.setItem(1,new ItemBuilder(Material.ORANGE_DYE,1).setName("§4Nur Teammitglieder").setLore("§8Dadurch sind nur noch Teammitglieder sichtbar für sie").create());
        inventory.setItem(2,new ItemBuilder(Material.RED_DYE,1).setName("§4Niemand").setLore("§8Dadurch sind keine Spieler für sie mehr sichtbar").create());
        player.openInventory(inventory);
    }

}
