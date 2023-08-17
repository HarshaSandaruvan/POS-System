package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO extends ItemDTO {
    private String customerId;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private int contactNo;
}
