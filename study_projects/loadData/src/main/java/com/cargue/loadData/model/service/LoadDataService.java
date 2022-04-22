package com.cargue.loadData.model.service;

import com.cargue.loadData.model.entity.LoadData;
import com.cargue.loadData.model.repository.LoadDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadDataService {

    private final LoadDataRepository dataRepository;

    @Autowired
    public LoadDataService(LoadDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveData(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("/home/cristian/Descargas/datos_example.csv"));

            while(reader.ready()){
                reader.skip(0);
                List<String> registry = Arrays.stream(reader.readLine().split(",")).collect(Collectors.toList());
                LoadData data = new LoadData();
                data.setDocumentId(registry.get(0));
                data.setIdDevice(registry.get(1));
                data.setSubscriptionId(registry.get(2));
                data.setModelId(registry.get(3));
                data.setuUIDCode(registry.get(4));
                data.setSerialSimCard(registry.get(5));
                data.setOperativeSystem(registry.get(6));
                data.setVersionOP(registry.get(7));
                data.setUserStatus(registry.get(8));
                data.setUserSTI(registry.get(9));
                data.setCreationDate(convertTextToDate(registry.get(10)));
                data.setUpdateUser(registry.get(11));
                data.setUpdateDate(convertTextToDate(registry.get(12)));
                data.setModelerIndicator(registry.get(13));
                data.setTypeDocumentCode(registry.get(14));
                data.setAccountStatus(registry.get(15));
                data.setDescriptionAccountStatus(registry.get(16));
                this.dataRepository.save(data);
            }
            reader.close();
        }catch(IOException ex){
            System.out.println(ex.getCause());
        }
    }

    private LocalDateTime convertTextToDate(String text){

        if(!text.isEmpty()){
            List<String> listDate = Arrays.stream(text.replaceAll("-", " ")
                    .split(" "))
                    .collect(Collectors.toList());

            LocalDate date = LocalDate.of(
                    Integer.parseInt(listDate.get(0)),
                    Integer.parseInt(listDate.get(1)),
                    Integer.parseInt(listDate.get(2)));

            DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH.mm.ss.nnn");
            LocalTime hour = LocalTime.parse(listDate.get(3), hourFormat);

            LocalDateTime dateTime = date.atTime(hour);
            return dateTime;
        }
        return null;
    }
}
