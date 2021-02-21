package ch.lu.mygym.dtos.plain;

import org.springframework.lang.NonNull;


import java.util.List;

public class WorkoutDTO {

    private int id;
    private String name;
    private String previewImageUrl;
    private byte[] previewImage;
    private UserDTO creator;
    private List<DayDTO> days;
    private boolean isSavedFromCurrentUser;

    private WorkoutDTO(
            int id,
            String name,
            String previewImageUrl,
            byte[] previewImage,
            UserDTO creator,
            List<DayDTO> days,
            boolean isSavedFromCurrentUser
            ) {
        this.id = id;
        this.name = name;
        this.previewImageUrl = previewImageUrl;
        this.previewImage = previewImage;
        this.creator = creator;
        this.days = days;
        this.isSavedFromCurrentUser = isSavedFromCurrentUser;
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

    public List<DayDTO> getDays() {
        return days;
    }

    public void setDays(List<DayDTO> days) {
        this.days = days;
    }

    public boolean isSavedFromCurrentUser() {
        return isSavedFromCurrentUser;
    }

    public static class WorkoutBuilder{

        private int id;
        private String name;
        private String imageUrl;
        private byte[] previewImage;
        private UserDTO creator;
        private List<DayDTO> days;
        private boolean isSavedFromCurrentUser;

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

        public WorkoutBuilder withDays(@NonNull List<DayDTO> days){
            this.days = days;
            return this;
        }

        public WorkoutBuilder withIsSavedFromCurrentUser(@NonNull boolean isSavedFromCurrentUser){
            this.isSavedFromCurrentUser = isSavedFromCurrentUser;
            return this;
        }

        public WorkoutDTO build(){
            WorkoutDTO workoutDTO = new WorkoutDTO(this.id, this.name, this.imageUrl, this.previewImage, this.creator, this.days, this.isSavedFromCurrentUser);
            return workoutDTO;
        }
    }
}
