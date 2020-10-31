package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "workout", schema = "public", catalog = "postgres")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workoutEntity", cascade = CascadeType.ALL)
    private List<SavedWorkoutsEntity> savedWorkoutsEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workoutEntity", cascade = CascadeType.ALL)
    private List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelationEntities;

    @Basic
    @Column(name = "image_url", nullable = true, length = -1)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private UserEntity userEntity;

    public List<PhaseDayExerciseRelationEntity> getPhaseDayExerciseRelationEntities() {
        return phaseDayExerciseRelationEntities;
    }

    public void setPhaseDayExerciseRelationEntities(List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelationEntities) {
        this.phaseDayExerciseRelationEntities = phaseDayExerciseRelationEntities;
    }

    public List<SavedWorkoutsEntity> getSavedWorkoutsEntity() {
        return savedWorkoutsEntity;
    }

    public void setSavedWorkoutsEntity(List<SavedWorkoutsEntity> savedWorkoutsEntity) {
        this.savedWorkoutsEntity = savedWorkoutsEntity;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutEntity that = (WorkoutEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
