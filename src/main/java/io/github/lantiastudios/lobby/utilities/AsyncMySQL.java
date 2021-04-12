package io.github.lantiastudios.lobby.utilities;

import io.github.lantiastudios.lobby.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class AsyncMySQL {
    private ExecutorService executor;
    private Plugin plugin;
    private MySQL sql;

    File file = new File("plugins/LobbySystem/mysql.yml");
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    Plugin owner = LobbySystem.getLobbySystemInstance();

    public AsyncMySQL() {
        if (!config.contains("HOST")) {
            config.set("HOST", "localhost");
        }
        if (!config.contains("USERNAME")) {
            config.set("USERNAME", "username");
        }
        if (!config.contains("PASSWORD")) {
            config.set("PASSWORD", "password");
        }
        if (!config.contains("DATABASE")) {
            config.set("DATABASE", "database");
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = config.getString("HOST");
        Integer port = 3306;
        String user = config.getString("USERNAME");
        String password = config.getString("PASSWORD");
        String database = config.getString("DATABASE");

        try {
            sql = new MySQL(host, port, user, password, database);
            executor = Executors.newCachedThreadPool();
            plugin = owner;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(PreparedStatement statement) {
        executor.execute(() -> sql.queryUpdate(statement));
    }

    public void update(String statement) {
        executor.execute(() -> sql.queryUpdate(statement));
    }

    public void query(PreparedStatement statement, Consumer<ResultSet> consumer) {
        executor.execute(() -> {
            ResultSet result = sql.query(statement);
            Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(result));
        });
    }

    public void query(String statement, Consumer<ResultSet> consumer) {
        executor.execute(() -> {
            ResultSet result = sql.query(statement);
            Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(result));
        });
    }

    public PreparedStatement prepare(String query) {
        try {
            return sql.getConnection().prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MySQL getMySQL() {
        return sql;
    }

    public static class MySQL {

        private String host, user, password, database;
        private int port;

        private Connection conn;

        public MySQL(String host, int port, String user, String password, String database) throws Exception {
            this.host = host;
            this.port = port;
            this.user = user;
            this.password = password;
            this.database = database;

            this.openConnection();
        }

        public void queryUpdate(String query) {
            checkConnection();
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                queryUpdate(statement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void queryUpdate(PreparedStatement statement) {
            checkConnection();
            try {
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public ResultSet query(String query) {
            checkConnection();
            try {
                return query(conn.prepareStatement(query));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public ResultSet query(PreparedStatement statement) {
            checkConnection();
            try {
                return statement.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public Connection getConnection() {
            return this.conn;
        }

        private void checkConnection() {
            try {
                if (this.conn == null || !this.conn.isValid(10) || this.conn.isClosed())
                    openConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Connection openConnection() throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            return this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
        }

        public void closeConnection() {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.conn = null;
            }
        }
    }
}
