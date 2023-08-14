package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ItemBo extends SuperBO {
    public Boolean saveItem(ItemDTO itemDTO);
    public String getNextId();

    public ObservableList<ItemDTO> getAllItem();

}
