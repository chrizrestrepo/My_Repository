package com.example.exercise.DTO;

import com.example.exercise.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    public ItemDTO(Long itemId, Integer amount, String product, Double price, Double totalAmount) {
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

    private Long itemId;
    private Integer amount;
    private String product;
    private Double price;
    private Double totalAmount;

    public static ItemDTO toDto(Item item){
        return new ItemDTO(item.getItemId(),
                item.getAmount(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.calculateAmount());
    }
}
