package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.plain.DayDTO;

public class WorkoutSetDTO {
    private int workoutId;
    private int userId;
    private DayDTO dayDTO;

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

    public DayDTO getDayDTO() {
        return dayDTO;
    }

    public void setDayDTO(DayDTO dayDTO) {
        this.dayDTO = dayDTO;
    }
}
