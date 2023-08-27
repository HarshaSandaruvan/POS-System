package dto;


import lombok.*;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDTO {
    private String orderID;
    private String customerID;
    private Time time;
    private Date date;
    private String cashierID;
    private double total;

}
