package com.example.exercise.DTO;

import com.example.exercise.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {

    public ItemDTO(String itemId, Integer amount, String product, Double price, Double totalAmount) {
        this.itemId = itemId;
        this.amount = amount;
        this.product = product;
        this.price = price;
        this.totalAmount = totalAmount;
    }

    public ItemDTO(Integer amount, String product) {
        this.amount = amount;
        this.product = product;
    }

    public ItemDTO(String itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    private String itemId;
    private Integer amount;
    private String product;
    private Double price;
    private Double totalAmount;

    public static ItemDTO toDto(Item item){
        return new ItemDTO(item.getItemId().toString(),
                item.getAmount(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.calculateAmount());
    }
}
