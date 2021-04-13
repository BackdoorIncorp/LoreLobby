package io.github.lantiastudios.lobby;

import com.google.common.base.Preconditions;
import io.github.lantiastudios.lobby.navigation.LobbyInventories;
import io.github.lantiastudios.lobby.sql.ItemAdapter;
import io.github.lantiastudios.lobby.utilities.AsyncMySQL;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class LobbySystem extends JavaPlugin  {

    private AsyncMySQL asyncMySQL = new AsyncMySQL();
    private static LobbySystem lobbySystemInstance;
    private final LobbyInventories lobbyInventories = new LobbyInventories();
    private final ItemAdapter itemAdapter = new ItemAdapter();
    @Override
    public void onEnable() {
        lobbySystemInstance = this;
        createMySQlTables();
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

    public LobbyInventories getLobbyInventories() {
        return lobbyInventories;
    }

    public ItemAdapter getItemAdapter() {
        return itemAdapter;
    }

    protected void createMySQlTables() {
        asyncMySQL.prepare("CREATE TABLE IF NOT EXISTS Items(material VARCHAR(64),platz int,name VARCHAR(64),lore VARCHAR(64),invitemtype VARCHAR(64))");

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
