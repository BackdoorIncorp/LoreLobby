package io.github.lantiastudios.lobby.utilities;

import io.github.lantiastudios.lobby.LobbySystem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.awt.*;

public class ActionBaarManager {

    public static void sendActionBar(Player player, String message) {
        Bukkit.getScheduler().runTaskAsynchronously(LobbySystem.getLobbySystemInstance(), new Runnable() {
            @Override
            public void run() {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
            }
        });
    }
}
