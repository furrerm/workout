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
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @OneToMany(mappedBy = "workoutEntity", cascade = CascadeType.ALL)
    private List<RoutineEntity> routines;

    public List<RoutineEntity> getRoutines() {
        return routines;
    }

    public void setRoutines(List<RoutineEntity> routines) {
        this.routines = routines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        WorkoutEntity that = (WorkoutEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
