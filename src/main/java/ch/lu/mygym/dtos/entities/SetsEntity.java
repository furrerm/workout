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
    private BigInteger weight;

    @Basic
    @Column(name = "repetitions", nullable = true, precision = 2)
    private BigInteger repetitions;

    @Basic
    @Column(name = "time", nullable = true)
    private LocalDateTime time;

    @Basic
    @Column(name = "BreakTime", nullable = true)
    private Integer breakTime;


    @ManyToOne
    @JoinColumn(name = "UserId", updatable = false, insertable = false)
    private UserEntity userEntity;

    @Basic
    @Column(name = "Order", nullable = true)
    private Integer order;


    @ManyToOne
    @JoinColumn(name = "ExerciseId")
    private PhaseDayExerciseRelationEntity phaseDayExerciseRelationEntity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }


    public BigInteger getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(BigInteger repetitions) {
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
