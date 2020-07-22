package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Day", schema = "public", catalog = "postgres")
public class DayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @OneToMany(mappedBy = "dayEntity", cascade = CascadeType.ALL)
    private List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelationEntities;


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
        DayEntity dayEntity = (DayEntity) o;
        return id == dayEntity.id &&
                Objects.equals(name, dayEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
