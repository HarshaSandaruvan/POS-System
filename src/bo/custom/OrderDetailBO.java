package bo.custom;

import bo.SuperBO;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;

public interface OrderDetailBO extends SuperBO {
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO);

    public String getNextOrderID();
}
