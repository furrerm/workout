package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Phase", schema = "public", catalog = "postgres")
public class PhaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @OneToMany(mappedBy = "phaseEntity", cascade = CascadeType.ALL)
    private List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelationEntites;

    public List<PhaseDayExerciseRelationEntity> getPhaseDayExerciseRelationEntites() {
        return phaseDayExerciseRelationEntites;
    }

    public void setPhaseDayExerciseRelationEntites(List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelationEntites) {
        this.phaseDayExerciseRelationEntites = phaseDayExerciseRelationEntites;
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
        PhaseEntity that = (PhaseEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
