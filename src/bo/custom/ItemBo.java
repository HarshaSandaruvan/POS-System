package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.ItemDTO;

public interface ItemBo extends SuperBO {
    public Boolean saveItem(ItemDTO itemDTO);
}
