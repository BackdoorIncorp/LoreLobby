package io.github.lantiastudios.lobby.navigation;

import io.github.lantiastudios.lobby.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LobbyInventories {


    public final void setJoinNavigator(Player player) {
        Inventory inventory = player.getInventory();
        inventory.setItem(4,new ItemBuilder(Material.MUSIC_DISC_11,1).setName("§4Navigation").setLore("Finden sie hier alle unsere Spielmodis").create());
        inventory.setItem(2,new ItemBuilder(Material.BREWING_STAND,1).setName("§3Spieler Verstecken").setLore("§8Stellen sie ihre Preferenzen ein welche Spieler sie sehen wollen!").create());
        inventory.setItem(8,new ItemBuilder(Material.PLAYER_HEAD,1).setSkullOwner(player.getDisplayName()).setName("§2Profil").setLore("§8Stelle ein was für Features du im LobbySystem verwenden willst!").create());
    }

    public final void openNavigation(Player player) {

    }

}
