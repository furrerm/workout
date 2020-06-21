package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "routine", schema = "public", catalog = "postgres")
public class RoutineEntity {
    private String name;


    @OneToMany(mappedBy = "routineEntity", cascade = CascadeType.ALL)
    private List<ExerciseEntity> exercises;

    @ManyToOne
    @JoinColumn(name = "workoutid")
    private WorkoutEntity workoutEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseEntity> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }

    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineEntity that = (RoutineEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
