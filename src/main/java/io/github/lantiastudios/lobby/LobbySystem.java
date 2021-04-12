package io.github.lantiastudios.lobby;

import com.google.common.base.Preconditions;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class LobbySystem extends JavaPlugin  {

    private static LobbySystem lobbySystemInstance;
    @Override
    public void onEnable() {
        lobbySystemInstance = this;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onDisable() {
    }

    public static LobbySystem getLobbySystemInstance() {
        return lobbySystemInstance;
    }
    public static class MainClassManager extends LobbySystem{

        public File file;
        public String name;
        public YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        public MainClassManager(String s) {
        this.name = s;
        file = new File(this.getDataFolder(),s);
        if(!file.exists()) {
            file.mkdirs();
        }
        }

        public YamlConfiguration getYamlConfiguration() {
            return yamlConfiguration;
        }
        public File getMainFile() {
            return this.file;
        }
        public String getFileName() {
            return this.getName();
        }
        public String getValue(String value) {
            return yamlConfiguration.getString(value);
        }
        public void setValue(String path,String value) {
            yamlConfiguration.set(path,value);
        }
    }
}
