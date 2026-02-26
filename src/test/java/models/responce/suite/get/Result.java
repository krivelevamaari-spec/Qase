package models.responce.suite.get;

import lombok.Data;
import models.responce.project.get.Entity;

import java.util.ArrayList;

@Data
public class Result {

    private int total;
    private int filtered;
    private int count;
    private ArrayList<Entity> entities;
}
