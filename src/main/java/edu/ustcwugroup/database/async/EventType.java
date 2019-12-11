package edu.ustcwugroup.database.async;

/**
 * Created by Haozk on 2019/12/10
 */
public enum EventType {
    SEARCH(1);

    EventType(int value){
        this.value=value;
    }
    private int value;
    public int getValue(){
        return value;
    }
}
