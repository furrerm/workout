package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Phase_Day_Exercise_Relation", schema = "public", catalog = "postgres")
public class PhaseDayExerciseRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "phase_order")
    private Integer phaseOrder;

    @Basic
    @Column(name = "exercise_order")
    private Integer exerciseOrder;

    @Basic
    @Column(name = "video_url")
    private String videoUrl;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;

    @Basic
    @Column(name = "time_length")
    private Integer timeLength;

    @Basic
    @Column(name = "time_based")
    private boolean timeBased;

    @Basic
    @Column(name = "weight")
    private boolean weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phase_id", updatable = false)
    private PhaseEntity phaseEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workoutEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id", updatable = false)
    private DayEntity dayEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id", updatable = false)
    private ExerciseEntity exerciseEntity;

    @OneToMany(mappedBy = "phaseDayExerciseRelationEntity", cascade = CascadeType.ALL)
    private List<SetsEntity> setsEntity;

    public Integer getPhaseOrder() {
        return phaseOrder;
    }

    public void setPhaseOrder(Integer phaseOrder) {
        this.phaseOrder = phaseOrder;
    }

    public Integer getExerciseOrder() {
        return exerciseOrder;
    }

    public void setExerciseOrder(Integer exerciseOrder) {
        this.exerciseOrder = exerciseOrder;
    }

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

    public DayEntity getDayEntity() {
        return dayEntity;
    }

    public void setDayEntity(DayEntity dayEntity) {
        this.dayEntity = dayEntity;
    }

    public ExerciseEntity getExerciseEntity() {
        return exerciseEntity;
    }

    public void setExerciseEntity(ExerciseEntity exerciseEntity) {
        this.exerciseEntity = exerciseEntity;
    }

    public List<SetsEntity> getSetsEntity() {
        return setsEntity;
    }

    public void setSetsEntity(List<SetsEntity> setsEntity) {
        this.setsEntity = setsEntity;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
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
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
