package edu.ustcwugroup.database.model;

/**
 * Created by Haozk on 2019/12/9
 */
public class Molecule {
    private int id;
    private String elements;
    private String formula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
