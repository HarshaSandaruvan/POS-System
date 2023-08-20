package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String contactNo;


}
