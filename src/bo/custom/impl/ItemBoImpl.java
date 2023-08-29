package bo.custom.impl;

import bo.custom.ItemBo;
import dao.CrudUtil;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        Item item = new Item(
                itemDTO.getItemID(),
                itemDTO.getItemName(),
                itemDTO.getBatchNumber(),
                itemDTO.getPrice(),
                itemDTO.getQty(),
                itemDTO.getSupplier(),
                itemDTO.getExpireDate()
        );

        return itemDAO.saveItem(item);
    }

    @Override
    public String getNextId() {
        String lastItemId = itemDAO.getLastItemId();
        int lastId = Integer.parseInt(lastItemId.substring(1));
        return String.format("I%03d",++lastId);
    }

    @Override
    public ObservableList<ItemDTO> getAllItem() {
        ArrayList<Item> allItems = itemDAO.getAllItems();
        ObservableList<ItemDTO> allItemsForTable = FXCollections.observableArrayList();
        for (Item a: allItems
        ) {
            allItemsForTable.add(new ItemDTO(
                    a.getItemID(),
                    a.getItemName(),
                    a.getBatchNumber(),
                    a.getPrice(),
                    a.getQty(),
                    a.getSupplier(),
                    a.getExpireDate()
            ));
        }
        return allItemsForTable;
    }

    @Override
    public ItemDTO getItemById(String itemID) {
        Item itemById = itemDAO.getItemByID(itemID);
        if(itemById != null){
            return new ItemDTO(
                    itemById.getItemID(),
                    itemById.getItemName(),
                    itemById.getBatchNumber(),
                    itemById.getPrice(),
                    itemById.getQty(),
                    itemById.getSupplier(),
                    itemById.getExpireDate()
            );
        }
        return null;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        return itemDAO.updateItem(new Item(
                itemDTO.getItemID(),
                itemDTO.getItemName(),
                itemDTO.getBatchNumber(),
                itemDTO.getPrice(),
                itemDTO.getQty(),
                itemDTO.getSupplier(),
                itemDTO.getExpireDate()
        ));
    }

    @Override
    public boolean deleteItem(String itemId) {
        return itemDAO.deleteItem(itemId);
    }

    @Override
    public ItemDTO getItemByItemName(String itemName) {
        Item itemByItemName = itemDAO.getItemByItemName(itemName);
        if(itemByItemName != null){
            return new ItemDTO(
                    itemByItemName.getItemID(),
                    itemByItemName.getItemName(),
                    itemByItemName.getBatchNumber(),
                    itemByItemName.getPrice(),
                    itemByItemName.getQty(),
                    itemByItemName.getSupplier(),
                    itemByItemName.getExpireDate()
            );
        }
        return null;
    }

    @Override
    public boolean updateQtyByItemId(String itemId, double qty) {
        return itemDAO.updateQtyByItemId(itemId,qty);
    }

    @Override
    public double getQtyByItemId(String itemId) {
       return itemDAO.getQtyByItemId(itemId);

    }
}
