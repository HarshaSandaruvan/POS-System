package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailTemp {
    private String orderID;
    private String itemID;

    private String itemName;
    private double qty;
    private double unitPrice;
    private double price;
}
