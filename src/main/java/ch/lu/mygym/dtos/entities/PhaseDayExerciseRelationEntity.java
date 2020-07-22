package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Phase_Day_Exercise_Relation", schema = "public", catalog = "postgres")
public class PhaseDayExerciseRelationEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Id", updatable = false, insertable = false)
    private PhaseEntity phaseEntity;

    @ManyToOne
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private WorkoutEntity workoutEntity;


    @ManyToOne
    @JoinColumn(name = "Id", updatable = false, insertable = false)
    private DayEntity dayEntity;

    @ManyToOne
    @JoinColumn(name = "Id", updatable = false, insertable = false)
    private ExerciseEntity exerciseEntity;

    @OneToMany(mappedBy = "phaseDayExerciseRelationEntity", cascade = CascadeType.ALL)
    private List<SetsEntity> setsEntity;

    public WorkoutEntity getWorkoutEntity() {
        return workoutEntity;
    }

    public void setWorkoutEntity(WorkoutEntity workoutEntity) {
        this.workoutEntity = workoutEntity;
    }

    public PhaseEntity getPhaseEntity() {
        return phaseEntity;
    }

    public void setPhaseEntity(PhaseEntity phaseEntity) {
        this.phaseEntity = phaseEntity;
    }

    public List<SetsEntity> getSetsEntity() {
        return setsEntity;
    }

    public void setSetsEntity(List<SetsEntity> setsEntity) {
        this.setsEntity = setsEntity;
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
        PhaseDayExerciseRelationEntity that = (PhaseDayExerciseRelationEntity) o;
        return id == that.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
