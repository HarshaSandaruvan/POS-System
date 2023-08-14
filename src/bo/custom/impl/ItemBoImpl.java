package bo.custom.impl;

import bo.custom.ItemBo;
import dao.CrudUtil;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;

public class ItemBoImpl implements ItemBo {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public Boolean saveItem(ItemDTO itemDTO) {
        Item item = new Item(
                itemDTO.getItemID(),
                itemDTO.getItemName(),
                itemDTO.getBatchNumber(),
                itemDTO.getPrice(),
                itemDTO.getQty(),
                itemDTO.getSupplier(),
                itemDTO.getExpireDate()
        );

        // save item via ItemDAO
        return itemDAO.saveItem(item);
    }


}
