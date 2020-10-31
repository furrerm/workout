package ch.lu.mygym.dtos.plain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ExerciseSetContainerDTO {
    private LocalDateTime timeOfExercise;
    private List<ExerciseSetDTO> exerciseSets;

    public ExerciseSetContainerDTO(LocalDateTime timeOfExercise, List<ExerciseSetDTO> exerciseSets){
        this.timeOfExercise = timeOfExercise;
        this.exerciseSets = exerciseSets;
    }

    public LocalDateTime getTimeOfExercise() {
        return timeOfExercise;
    }

    public List<ExerciseSetDTO> getExerciseSets() {
        return exerciseSets;
    }

    public static class Builder {
        private LocalDateTime timeOfExercise;
        private List<ExerciseSetDTO> exerciseSets;

        public Builder withTimeOfExercise(LocalDateTime timeOfExercise){
            this.timeOfExercise = timeOfExercise;
            return this;
        }
        public Builder withExerciseSets(List<ExerciseSetDTO> exerciseSets){
            this.exerciseSets = exerciseSets;
            return this;
        }
        public ExerciseSetContainerDTO build(){
            return new ExerciseSetContainerDTO(this.timeOfExercise, this.exerciseSets);
        }
    }
}
