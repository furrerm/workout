package ch.lu.mygym.dtos.plain;

import java.util.List;

public class SupersetDTO {
    private String name;
    private String date;
    private List<SetDTO> sets;

    public SupersetDTO(String name, List<SetDTO> sets) {
        this.name = name;
        this.sets = sets;
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
}
