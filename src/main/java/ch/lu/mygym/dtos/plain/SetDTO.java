package ch.lu.mygym.dtos.plain;

import java.math.BigInteger;

public class SetDTO {
    private int id;
    private BigInteger weight;
    private BigInteger repetitions;

    public SetDTO() {
    }

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
}
