package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ItemBo extends SuperBO {
    public boolean saveItem(ItemDTO itemDTO);
    public String getNextId();

    public ObservableList<ItemDTO> getAllItem();

    public ItemDTO getItemById(String itemID);

    public boolean updateItem(ItemDTO itemDTO);

    public boolean deleteItem(String itemId);
    public ItemDTO getItemByItemName(String itemName);

    public boolean updateQtyByItemId(String itemId, double qty);

    public double getQtyByItemId(String itemId);
}
