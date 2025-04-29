package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataClass {
    private ObservableList<Person> importList;
    private ObservableList<Person> exportList;

    public DataClass() {
        importList = FXCollections.observableArrayList();
        importList.add(new Person("Sami", "BenAli", "sami@exemple.com"));
        importList.add(new Person("Alia", "BenSalah", "alia@exemple.com"));
        importList.add(new Person("Ali", "BenSalah", "ali@exemple.com"));
        exportList = FXCollections.observableArrayList();

    }

    public ObservableList<Person> getImportList() {
        return importList;
    }

    public void setImportList(ObservableList<Person> importList) {
        this.importList = importList;
    }

    public ObservableList<Person> getExportList() {
        return exportList;
    }

    public void setExportList(ObservableList<Person> exportList) {
        this.exportList = exportList;
    }

    public void setExportList(List<Person> exportList) {
        this.exportList.addAll(exportList);
        for (Person p : this.exportList)
            System.out.println(p);
    }

}