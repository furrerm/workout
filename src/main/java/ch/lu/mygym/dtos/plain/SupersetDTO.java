package ch.lu.mygym.dtos.plain;

import java.util.List;

public class SupersetDTO {
    private int id;
    private String name;
    private String date;
    private List<SetDTO> sets;

    public SupersetDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SetDTO> getSets() {
        return sets;
    }

    public void setSets(List<SetDTO> sets) {
        this.sets = sets;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
