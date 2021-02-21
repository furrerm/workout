package ch.lu.mygym.dtos.plain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public class PhaseDTO implements Comparable{

    private int id;
    private String name;
    private List<ExerciseDTO> exercises;
    private int order;

    private PhaseDTO(int id, String name, List<ExerciseDTO> exercises, int order) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
        this.order = order;
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

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    @Override
    public int compareTo(Object o) {
        return this.order - ((PhaseDTO)o).getOrder();
    }

    public static class Builder {
        private int id;
        private String name;
        private List<ExerciseDTO> exercises;
        private int order;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withExercises(List<ExerciseDTO> exercises){
            this.exercises = exercises;
            return this;
        }

        public Builder withOrder(int order){
            this.order = order;
            return this;
        }

        public PhaseDTO build(){
            PhaseDTO phaseDTO = new PhaseDTO(this.id, this.name, this.exercises, this.order);
            return phaseDTO;
        }
    }
}
