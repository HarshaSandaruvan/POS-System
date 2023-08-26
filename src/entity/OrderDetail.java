package entity;

import lombok.*;

import java.security.SecureRandom;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private String orderID;
    private String itemID;

    private String itemName;
    private double qty;
    private double unitPrice;
    private double price;
}
