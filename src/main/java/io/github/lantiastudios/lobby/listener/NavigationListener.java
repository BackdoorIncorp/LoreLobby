package io.github.lantiastudios.lobby.listener;


import io.github.lantiastudios.lobby.LobbySystem;
import io.github.lantiastudios.lobby.navigation.LobbyInventories;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NavigationListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();

        if(itemStack.getType().equals(Material.MUSIC_DISC_11)) {
            if(itemStack.getItemMeta().getDisplayName().matches("§4Navigation")) {
                boolean b = action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK);
                if(b) {
                    LobbySystem.getLobbySystemInstance().getLobbyInventories().openNavigation(player);
                }
            }
        }else if(itemStack.getType().equals(Material.BREWING_STAND)) {
            if(itemStack.getItemMeta().getDisplayName().matches("§3Spieler Verstecken")) {
                final boolean b = action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK);
                if(b) {
                    LobbySystem.getLobbySystemInstance().getLobbyInventories().openPlayerHiderInventory(player);
                }
            }
        }
    }

}
