package models.responce.project.get;

import lombok.Data;

@Data
public class Counts {

    private int cases;
    private int suites;
    private int milestones;
    private Runs runs;
    private Defects defects;
}
