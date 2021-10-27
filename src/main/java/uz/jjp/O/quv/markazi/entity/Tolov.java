package uz.jjp.O.quv.markazi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tolov {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int qiymat;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private LocalDateTime tolovVaqt;
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public int getQiymat() {
        return qiymat;
    }

    public void setQiymat(int qiymat) {
        this.qiymat = qiymat;
    }

    public LocalDateTime getTolovVaqt() {
        return tolovVaqt;
    }

    public void setTolovVaqt(LocalDateTime tolovVaqt) {
        this.tolovVaqt = tolovVaqt;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
