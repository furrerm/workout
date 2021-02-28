package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "exercise", schema = "public", catalog = "postgres")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Basic
    @Column(name = "video_url", nullable = true, length = 255)
    private String videoUrl;

    @Basic
    @Column(name = "image")
    private String image;

    @Basic
    @Column(name = "user_entry_required")
    private boolean userEntryRequired;

    @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isUserEntryRequired() {
        return userEntryRequired;
    }

    public void setUserEntryRequired(boolean userEntryRequired) {
        this.userEntryRequired = userEntryRequired;
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
