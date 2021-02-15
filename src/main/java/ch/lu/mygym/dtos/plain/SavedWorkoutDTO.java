package ch.lu.mygym.dtos.plain;

public class SavedWorkoutDTO {

    private int workoutId;
    private int userId;

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
