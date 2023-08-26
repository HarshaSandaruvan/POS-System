package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    @Override
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail=new OrderDetail(
                orderDetailDTO.getOrderID(),
                orderDetailDTO.getItemID(),
                orderDetailDTO.getItemName(),
                orderDetailDTO.getQty(),
                orderDetailDTO.getUnitPrice(),
                orderDetailDTO.getPrice()
        );
        return orderDetailDAO.saveOrderDetail(orderDetail);
    }

    @Override
    public String getNextOrderID() {
        String lastItemId = orderDetailDAO.getLastOrderId();
        int lastId = Integer.parseInt(lastItemId.substring(1));
        return String.format("O%04d",++lastId);

    }
}
