package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean saveItem(Item item) {

        try {
            return CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?,?)",
                    item.getItemID(),
                    item.getItemName(),
                    item.getBatchNumber(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupplier(),
                    item.getExpireDate()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getLastItemId() {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT*FROM item ORDER BY itemID DESC LIMIT 1");
            if(resultSet.next()){
                return resultSet.getString("itemID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "I000";
    }

    @Override
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item");
            while(resultSet.next()){
                allItems.add(new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return allItems;
    }



    @Override
    public Item getItemByID(String itemID) {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemID=?", itemID);
            if(resultSet.next()){
                return new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean updateItem(Item item) {
        try {
            return CrudUtil.executeUpdate("UPDATE item SET itemName=?, batchNumber=?, price=?, " +
                            "qty=?, supplier=?, expireDate=? WHERE itemID=?",
                    item.getItemName(),
                    item.getBatchNumber(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupplier(),
                    item.getExpireDate(),
                    item.getItemID()
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteItem(String itemId) {
        try {
            boolean b;
            if (CrudUtil.executeUpdate("DELETE FROM item WHERE itemId=?", itemId)) {
                b = true;
            }else{
                b = false;
            }
            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Item getItemByItemName(String itemName) {

        try {
            ResultSet selectedItem = CrudUtil.executeQuery("SELECT * FROM item WHERE itemName=?;",itemName);
            if (selectedItem.first()){
                return new Item(
                        selectedItem.getString("itemID"),
                        selectedItem.getString("itemName"),
                        selectedItem.getString("batchNumber"),
                        selectedItem.getDouble("price"),
                        selectedItem.getDouble("qty"),
                        selectedItem.getString("supplier"),
                        selectedItem.getDate("expireDate")

                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        return null;
    }
}
