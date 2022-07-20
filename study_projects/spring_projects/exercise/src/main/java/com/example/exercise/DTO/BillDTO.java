package com.example.exercise.DTO;

import com.example.exercise.model.Bill;
import com.example.exercise.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO {

    public BillDTO(Long billId, String description, String observation, Date createAt, Date createHour, Long clientId) {
        this.billId = billId;
        this.description = description;
        this.observation = observation;
        this.createAt = createAt;
        this.createHour = createHour;
        this.clientId = clientId;
    }

    private Long billId;
    private String description;
    private String observation;
    private Date createAt;
    private Date createHour;
    private Long clientId;
    private List<Object> items = new ArrayList<>();
    private Double total;

    public static BillDTO toDto(Bill bill){
        BillDTO billDTO = new BillDTO(bill.getBillId(),
                bill.getDescription(),
                bill.getObservation(),
                bill.getCreateAt(),
                bill.getCreateHour(),
                bill.getClient().getClientId());

        billDTO.getItems().add(bill.getItems()
                .stream()
                .map(ItemDTO::toDto)
                .collect(Collectors.toList()));

        billDTO.setTotal(bill.getTotalBill());

        return billDTO;
    }
}
