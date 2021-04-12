package io.github.lantiastudios.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddNewItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            //1 Material , 2 platz , 3 Name , 4 Lore
            if(strings.length == 4) {
                final String MATERIAL = strings[0];
                final int PLACE = Integer.valueOf(strings[1]);
                final String NAME = strings[2];
                final String LORE = strings[3];


            }
        }
        return false;
    }
}
