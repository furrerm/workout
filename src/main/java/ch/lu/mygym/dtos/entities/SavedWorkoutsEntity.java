package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SavedWorkouts", schema = "public", catalog = "postgres")
public class SavedWorkoutsEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "workout_id", insertable=false, updatable=false)
    private WorkoutEntity workoutEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public WorkoutEntity getWorkoutEntity() {
        return workoutEntity;
    }

    public void setWorkoutEntity(WorkoutEntity workoutEntity) {
        this.workoutEntity = workoutEntity;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedWorkoutsEntity that = (SavedWorkoutsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
