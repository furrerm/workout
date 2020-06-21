package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sets", schema = "public", catalog = "postgres")
public class SetsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private BigInteger weight;
    private BigInteger repetitions;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exerciseEntity;

    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "weight")
    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "repetitions")
    public BigInteger getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(BigInteger repetitions) {
        this.repetitions = repetitions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExerciseEntity getExerciseEntity() {
        return exerciseEntity;
    }

    public void setExerciseEntity(ExerciseEntity exerciseEntity) {
        this.exerciseEntity = exerciseEntity;
    }

    @Basic
    @Column(name = "time")
    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetsEntity that = (SetsEntity) o;
        return id == that.id &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(repetitions, that.repetitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, repetitions);
    }
}
