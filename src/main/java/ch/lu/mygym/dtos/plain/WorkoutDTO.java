package ch.lu.mygym.dtos.plain;

import ch.lu.mygym.dtos.entities.UserEntity;


import java.util.List;

public class WorkoutDTO {
    private int id;
    private String name;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
