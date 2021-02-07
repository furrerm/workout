package ch.lu.mygym.dtos.plain;

import org.springframework.lang.NonNull;


import java.util.Optional;
import java.util.SortedSet;

public class WorkoutDTO {

    private int id;
    private String name;
    private String previewImageUrl;
    private byte[] previewImage;
    private UserDTO creator;
    private SortedSet<DayDTO> days;

    private WorkoutDTO(
            int id,
            String name,
            String previewImageUrl,
            byte[] previewImage,
            UserDTO creator,
            SortedSet<DayDTO> days) {
        this.id = id;
        this.name = name;
        this.previewImageUrl = previewImageUrl;
        this.previewImage = previewImage;
        this.creator = creator;
        this.days = days;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPreviewImageUrl() {
        return this.previewImageUrl;
    }

    public byte[] getPreviewImage() {
        return previewImage;
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
        private byte[] previewImage;
        private UserDTO creator;
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

        public WorkoutBuilder withPreviewImage(@NonNull byte[] previewImage){
            this.previewImage = previewImage;
            return this;
        }

        public WorkoutBuilder withCreator(@NonNull UserDTO creator){
            this.creator =creator;
            return this;
        }

        public WorkoutBuilder withDays(@NonNull SortedSet<DayDTO> days){
            this.days = days;
            return this;
        }

        public WorkoutDTO build(){
            WorkoutDTO workoutDTO = new WorkoutDTO(this.id, this.name, this.imageUrl, this.previewImage, this.creator, this.days);
            return workoutDTO;
        }
    }
}
