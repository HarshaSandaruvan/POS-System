package dao.custom;

import dao.SuperDAO;
import entity.Item;

public interface ItemDAO extends SuperDAO {
    public boolean saveItem(Item item);
    public String getLastItemId();
}
