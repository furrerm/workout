package ch.lu.mygym.dtos.plain;

import ch.lu.mygym.dtos.entities.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public class WorkoutDTO {

    private int id;
    private String name;
    private String imageUrl;
    private UserDTO creator;
    private SortedSet<DayDTO> days;

    private WorkoutDTO(int id, String name, String imageUrl, SortedSet<DayDTO> days) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.days = days;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public UserDTO getCreator() {
        return this.creator;
    }

    public SortedSet<DayDTO> getDays() {
        return days;
    }

    public void setDays(SortedSet<DayDTO> days) {
        this.days = days;
    }

    public static class WorkoutBuilder{

        private int id;
        private String name;
        private String imageUrl;
        private Optional<UserDTO> creator;
        private SortedSet<DayDTO> days;

        public WorkoutBuilder withId(@NonNull int id){
            this.id = id;
            return this;
        }

        public WorkoutBuilder withName(@NonNull String name){
            this.name = name;
            return this;
        }

        public WorkoutBuilder withImageUrl(@NonNull String imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }

        public WorkoutBuilder withCreator(@NonNull UserDTO creator){
            this.creator = Optional.of(creator);
            return this;
        }

        public WorkoutBuilder withDays(@NonNull SortedSet<DayDTO> days){
            this.days = days;
            return this;
        }

        public WorkoutDTO build(){
            WorkoutDTO workoutDTO = new WorkoutDTO(this.id, this.name, this.imageUrl, this.days);
            return workoutDTO;
        }
    }
}
