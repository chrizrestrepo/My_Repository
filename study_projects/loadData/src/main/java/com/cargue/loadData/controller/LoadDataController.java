package com.cargue.loadData.controller;

import com.cargue.loadData.model.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/load")
public class LoadDataController {

    private final LoadDataService loadDataService;

    @Autowired
    public LoadDataController(LoadDataService loadDataService) {
        this.loadDataService = loadDataService;
    }

    @PostMapping(path = "save")
    public void saveData(){
        loadDataService.saveData();
    }
}
