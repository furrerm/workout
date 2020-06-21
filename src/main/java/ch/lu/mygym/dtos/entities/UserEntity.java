package ch.lu.mygym.dtos.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "public", catalog = "postgres")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userid")
    private int userid;

    @Basic
    @Column(name = "remoteid")
    private String remoteid;

    @Basic
    @Column(name = "firstname")
    private String firstname;

    @Basic
    @Column(name = "lastname")
    private String lastname;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "photourl")
    private String photourl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<WorkoutEntity> workoutEntities;


    public List<WorkoutEntity> getWorkoutEntities() {
        return workoutEntities;
    }

    public void setWorkoutEntities(List<WorkoutEntity> workoutEntities) {
        this.workoutEntities = workoutEntities;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public String getRemoteid() {
        return remoteid;
    }

    public void setRemoteid(String remoteid) {
        this.remoteid = remoteid;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userid == that.userid &&
                Objects.equals(remoteid, that.remoteid) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(photourl, that.photourl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, remoteid, firstname, lastname, email, photourl);
    }
}
