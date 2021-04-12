package io.github.lantiastudios.lobby.listener;

import io.github.lantiastudios.lobby.LobbySystem;
import io.github.lantiastudios.lobby.navigation.LobbyInventories;
import io.github.lantiastudios.lobby.utilities.TitleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(player.hasPlayedBefore()) {

        }else {
            TitleManager.sendTitle(player,1,3,1,"&3Kuschellstube","&3Let's Go");
            LobbySystem.getLobbySystemInstance().getLobbyInventories().setJoinNavigator(player);
        }
    }
}
