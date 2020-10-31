package ch.lu.mygym.dtos.plain;

import java.math.BigInteger;

public class ExerciseSetDTO {
    private int id;
    private float weight;
    private int repetitions;

    public ExerciseSetDTO(int id, float weight, int repetitions) {
        this.id = id;
        this.weight = weight;
        this.repetitions = repetitions;
    }

    public int getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public int getRepetitions() {
        return repetitions;
    }
    public static class Builder {
        private int id;
        private float weight;
        private int repetitions;

        public Builder withId(int id){
            this.id = id;
            return this;
        }
        public Builder withWeight(float weight){
            this.weight = weight;
            return this;
        }
        public Builder withRepetitions(int repetitions){
            this.repetitions = repetitions;
            return this;
        }
        public ExerciseSetDTO build(){
            return new ExerciseSetDTO(this.id, this.weight, this.repetitions);
        }
    }
}
