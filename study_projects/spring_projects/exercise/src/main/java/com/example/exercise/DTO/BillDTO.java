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

    public BillDTO(Long billId, Long clientId, String description, String observation, Date createAt, Date createHour) {
        this.billId = billId;
        this.description = description;
        this.observation = observation;
        this.createAt = createAt;
        this.createHour = createHour;
        this.clientId = clientId;
    }

    private Long billId;
    private Long clientId;
    private String description;
    private String observation;
    private Date createAt;
    private Date createHour;
    private List<Object> items = new ArrayList<>();
    private Double total;

    public static BillDTO toDto(Bill bill){
        BillDTO billDTO = new BillDTO(bill.getBillId(),
                bill.getClient().getClientId(),
                bill.getDescription(),
                bill.getObservation(),
                bill.getCreateAt(),
                bill.getCreateHour());

        billDTO.getItems().add(bill.getItems()
                .stream()
                .map(ItemDTO::toDto)
                .collect(Collectors.toList()));

        billDTO.setTotal(bill.getTotalBill());

        return billDTO;
    }
}
