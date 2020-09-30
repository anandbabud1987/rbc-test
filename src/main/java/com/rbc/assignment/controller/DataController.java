package com.rbc.assignment.controller;

import com.rbc.assignment.domain.DataSet;
import com.rbc.assignment.model.AddNewRecordRq;
import com.rbc.assignment.model.FileRs;
import com.rbc.assignment.service.DataService;
import com.rbc.assignment.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.FactoryConfigurationError;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/datasets")
public class DataController {
    private final ServiceUtil serviceUtil;
    private final DataService dataService;
    @Autowired
    public DataController(ServiceUtil serviceUtil,DataService dataService){
        this.serviceUtil=serviceUtil;
        this.dataService=dataService;
    }
    @PostMapping(produces = "application/json",consumes = "multipart/form-data")
    public ResponseEntity<FileRs> uploadBulkData(@RequestBody @RequestParam("bulk_data") MultipartFile file) throws IOException {
        //Step 1: Validate file
        serviceUtil.validate(file);

        //Step 2: Store file to disk
        dataService.saveFile(file);

        //Step 2: Return response
        return dataService.prepareResponse();
    }

    @PatchMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<FileRs> addNewRecord(@RequestBody DataSet dataSet) throws IOException {


        //Step 2: Store file to disk
        dataService.addNewRecord(dataSet);

        //Step 2: Return response
        return dataService.prepareResponse();
    }

    @GetMapping
    public ResponseEntity<List> getDataSet(@RequestParam("stock") String stock){
        serviceUtil.validateStock(stock);

        List response = dataService.queryDataSet(stock);

        return dataService.prepareResponse(response);


    }


}
