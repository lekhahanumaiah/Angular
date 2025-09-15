
package com.example.demo.model.DTO;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private String fullName;
    private String customerEmail;
    private String customerAddress;
    private Double totalAmount;
    private List<OrderItemDTO> items;

    public String getFullName() {
        return fullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
    public String getCustomerAddress() {
       return  customerAddress;
    }


    public Double getTotalAmount() {
        return totalAmount;
    }
    public List<OrderItemDTO> getItems() {
        return items;
    }




}
