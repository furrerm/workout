package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "exercise", schema = "public", catalog = "postgres")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private RoutineEntity routineEntity;

    @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
    private List<SetsEntity> setsEntities;

    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "name")
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
        ExerciseEntity that = (ExerciseEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
