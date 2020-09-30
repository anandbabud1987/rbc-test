package com.rbc.assignment.service;

import com.rbc.assignment.domain.DataSet;
import com.rbc.assignment.model.FileRs;
import com.rbc.assignment.repository.DataSetCustomImpl;
import com.rbc.assignment.repository.DataSetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


/**
 * This is a Service for Data controller
 */
@Service
public class DataService {

    private final DataSetRepository dataSetRepository;

    public DataService(DataSetCustomImpl dataSetCustomImpl,DataSetRepository dataSetRepository) {
        this.dataSetRepository = dataSetRepository;
    }

    /**
     * To prepare response
     *
     * @return
     */
    public ResponseEntity<FileRs> prepareResponse() {
        return new ResponseEntity<>(
                FileRs
                        .builder()
                        .errorCode(0)
                        .errorMessage("success")
                        .build()
                , HttpStatus.OK);
    }
    public ResponseEntity<List> prepareResponse(List list) {
        return new ResponseEntity<>(
                list
                , HttpStatus.OK);
    }
    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        if(convFile.exists()){
            convFile.delete();
        }
        convFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    public void saveFile(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        int count=0;
        while (line != null) {
            if (count != 0) {
                String[] splitedArray = line.split(",");
                dataSetRepository.save( mapToStore(splitedArray));
            }
            line = bufferedReader.readLine();

            count++;
        }

    }

    private DataSet mapToStore(String[] splitedArray) {
        return DataSet.builder()
                .quarter(splitedArray[0])
                .stock(splitedArray[1])
                .date(splitedArray[2])
                .open(splitedArray[3])
                .high(splitedArray[4])
                .low(splitedArray[5])
                .close(splitedArray[6])
                .volume(splitedArray[7])
                .percent_change_price(splitedArray[8])
                .percent_change_volume_over_last_wk(splitedArray[9])
                .previous_weeks_volume(splitedArray[10])
                .next_weeks_open(splitedArray[11])
                .next_weeks_close(splitedArray[12])
                .percent_change_next_weeks_price(splitedArray[13])
                .days_to_next_dividend(splitedArray[14])
                .percent_return_next_dividend(splitedArray[15])
                .build();
    }

    public List queryDataSet(String stock) {
       return dataSetRepository.findByStock(stock);
    }

    public void addNewRecord(DataSet dataSet) {
        dataSetRepository.save(dataSet);
    }
}
