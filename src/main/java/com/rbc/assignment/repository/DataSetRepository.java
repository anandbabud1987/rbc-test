package com.rbc.assignment.repository;

import com.rbc.assignment.domain.DataSet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DataSetRepository  extends CrudRepository<DataSet, Long>,DataSetCustom {

    @Override
    Optional<DataSet> findById(Long aLong);

    @Override
    Iterable<DataSet> findAll();


}
