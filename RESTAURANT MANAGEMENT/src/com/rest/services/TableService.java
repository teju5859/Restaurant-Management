package com.rest.services;

import com.rest.models.table;
import com.rest.repository.tablerepository;
import java.util.List;

public class TableService {

    private tablerepository repository;

    public TableService() {
        repository = new tablerepository();
    }

    public List<table> getAllTables(){
        return repository.findAll();
    }
}