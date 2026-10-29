package com.rest.models;

public class table {

    public enum AVAILABILITY {
        FREE,
        OCCUPIED
    }
    
    private int id;
    private int capacity;
    private AVAILABILITY status;

    public table (int id,int capacity){
        this.id=id;
        this.capacity=capacity;
        this.status=AVAILABILITY.FREE;
    }

    public int getId(){
        return id;
    }
    public int getcapacity(){
        return capacity;
    }

    public AVAILABILITY getStatus(){
        return status;
    }
    public void freeTable(){
        this.status=AVAILABILITY.FREE;
    }

    public void occupyTable(){
        this.status=AVAILABILITY.OCCUPIED;
    }

    @Override
    public String toString() {
        return "Table{id=" + id + ", capacity=" + capacity + ", status=" + status + "}";
    }
    
}