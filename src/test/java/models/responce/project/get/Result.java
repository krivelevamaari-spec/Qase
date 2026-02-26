package models.responce.project.get;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Result {

    private int total;
    private int filtered;
    private int count;
    private ArrayList<Entity> entities;
}
