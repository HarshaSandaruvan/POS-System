package dao.custom;

import dao.SuperDAO;
import entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends SuperDAO {
    public boolean saveItem(Item item);
    public String getLastItemId();
    public ArrayList<Item> getAllItems();
    public Item getItemByID(String itemID);

    public Boolean updateItem(Item item);
    public Boolean deleteItem(String itemId);
}
