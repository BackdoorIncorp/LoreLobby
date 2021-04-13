package io.github.lantiastudios.lobby.sql;

import io.github.lantiastudios.lobby.utilities.AsyncMySQL;
import org.bukkit.Material;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


public class ItemAdapter extends AsyncMySQL {

    public void createNewItem(Material material, int place, String name, @Nullable InventoryItemType inventoryItemType, String lore) throws SQLException {
        PreparedStatement preparedStatement = prepare("INSERT INTO Items(material,platz,name,lore,invitemtype) VALUES (?,?,?,?,?)");
        preparedStatement.setString(1,material.toString());
        preparedStatement.setInt(2,place);
        preparedStatement.setString(3,name);
        preparedStatement.setString(4,lore);
        preparedStatement.setString(5,inventoryItemType.toString());
        preparedStatement.executeUpdate();
    }
    public List<Item> getItemByInventoryType(InventoryItemType type) {
        List list = null;
        try {
            PreparedStatement preparedStatement = prepare("SELECT * FROM Items WHERE invitemtype = ?");
            preparedStatement.setString(1,type.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                 list = Collections.singletonList(new ArrayList<>().add(new Item(Material.valueOf(resultSet.getString("material")),
                        resultSet.getString("name"),
                        resultSet.getInt("platz"),
                        resultSet.getString("lore"),
                        InventoryItemType.valueOf(resultSet.getString("invitemtype")))));
            }
            return list;
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
