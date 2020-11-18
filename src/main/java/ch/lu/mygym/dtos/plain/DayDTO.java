package ch.lu.mygym.dtos.plain;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public class DayDTO implements Comparable{

    private int id;
    private String name;
    private List<PhaseDTO> phases;

    public DayDTO(int id, String name, List<PhaseDTO> days) {
        this.id = id;
        this.name = name;
        this.phases = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhases(List<PhaseDTO> phases) {
        this.phases = phases;
    }

    public List<PhaseDTO> getPhases() {
        return phases;
    }

    @Override
    public int compareTo(Object o) {
        return this.getId() - ((DayDTO)o).getId();
    }

    public static class Builder {
        private int id;
        private String name;
        private List<PhaseDTO> phases;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withPhases(List<PhaseDTO> phases){
            this.phases = phases;
            return this;
        }

        public DayDTO build(){
            DayDTO dayDTO = new DayDTO(this.id, this.name, this.phases);
            return dayDTO;
        }
    }
}
