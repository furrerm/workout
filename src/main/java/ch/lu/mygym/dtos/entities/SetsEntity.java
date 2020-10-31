package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sets", schema = "public", catalog = "postgres")
public class SetsEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "weight", nullable = true, precision = 2)
    private float weight;

    @Basic
    @Column(name = "repetitions", nullable = true, precision = 2)
    private int repetitions;

    @Basic
    @Column(name = "time", nullable = true)
    private LocalDateTime time;

    @Basic
    @Column(name = "break_time", nullable = true)
    private Integer breakTime;

    @Basic
    @Column(name = "exercise_id", nullable = true)
    private Integer exerciseId;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private UserEntity userEntity;

    @Basic
    @Column(name = "order", nullable = true)
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "phase_day_ex_id", updatable = false, insertable = false)
    private PhaseDayExerciseRelationEntity phaseDayExerciseRelationEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }



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


    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


}
