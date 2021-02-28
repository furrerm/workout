package ch.lu.mygym.dtos.plain;

import java.util.Set;

public class ExerciseDTO implements Comparable{
    private int id;
    private String name;
    private Set<ExerciseSetContainerDTO> setsContainer;
    private int order;
    private String videoUrl;
    private String image;
    private boolean userEntryRequired;
    private int timeLength;
    private boolean timeBased;
    private boolean weight;

    public ExerciseDTO(
            int id,
            String name,
            Set<ExerciseSetContainerDTO> setsContainer,
            int order,
            String videoUrl,
            String image,
            boolean userEntryRequired,
            int timeLength,
            boolean timeBased,
            boolean weight
            ) {
        this.id = id;
        this.name = name;
        this.setsContainer = setsContainer;
        this.order = order;
        this.videoUrl = videoUrl;
        this.image = image;
        this.userEntryRequired = userEntryRequired;
        this.timeLength = timeLength;
        this.timeBased = timeBased;
        this.weight = weight;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isUserEntryRequired() {
        return userEntryRequired;
    }

    public void setUserEntryRequired(boolean userEntryRequired) {
        this.userEntryRequired = userEntryRequired;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public boolean isTimeBased() {
        return timeBased;
    }

    public void setTimeBased(boolean timeBased) {
        this.timeBased = timeBased;
    }

    public boolean isWeight() {
        return weight;
    }

    public void setWeight(boolean weight) {
        this.weight = weight;
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
        private String videoUrl;
        private String image;
        private boolean userEntityRequired;
        private int timeLength;
        private boolean timeBased;
        private boolean weight;

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

        public Builder withVideoUrl(String videoUrl){
            this.videoUrl = videoUrl;
            return this;
        }

        public Builder withImage(String image){
            this.image = image;
            return this;
        }

        public Builder withUserEntryRequired(boolean userEntryRequired){
            this.userEntityRequired = userEntryRequired;
            return this;
        }

        public Builder withTimeLength(int timeLength){
            this.timeLength = timeLength;
            return this;
        }

        public Builder withTimeBased(boolean timeBased){
            this.timeBased = timeBased;
            return this;
        }

        public Builder withWeight(boolean weight){
            this.weight = weight;
            return this;
        }

        public ExerciseDTO build(){
            ExerciseDTO exerciseDTO = new ExerciseDTO(this.id, this.name, this.sets, this.order, this.videoUrl, this.image, this.userEntityRequired, this.timeLength, this.timeBased, this.weight);
            return exerciseDTO;
        }
    }
}
