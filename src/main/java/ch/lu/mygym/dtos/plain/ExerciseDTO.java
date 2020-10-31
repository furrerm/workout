package ch.lu.mygym.dtos.plain;

import java.util.Set;

public class ExerciseDTO implements Comparable{
    private int id;
    private String name;
    private Set<ExerciseSetContainerDTO> setsContainer;
    private int order;

    public ExerciseDTO(int id, String name, Set<ExerciseSetContainerDTO> setsContainer, int order) {
        this.id = id;
        this.name = name;
        this.setsContainer = setsContainer;
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

    public Set<ExerciseSetContainerDTO> getSetsContainer() {
        return setsContainer;
    }

    public void setSetsContainer(Set<ExerciseSetContainerDTO> setsContainer) {
        this.setsContainer = setsContainer;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(Object o) {
        return this.getOrder() - ((ExerciseDTO)o).getOrder();
    }

    public static class Builder {
        private int id;
        private String name;
        private Set<ExerciseSetContainerDTO> sets;
        private int order;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withSets(Set<ExerciseSetContainerDTO> sets){
            this.sets = sets;
            return this;
        }

        public Builder withOrder(int order){
            this.order = order;
            return this;
        }

        public ExerciseDTO build(){
            ExerciseDTO exerciseDTO = new ExerciseDTO(this.id, this.name, this.sets, this.order);
            return exerciseDTO;
        }
    }
}
