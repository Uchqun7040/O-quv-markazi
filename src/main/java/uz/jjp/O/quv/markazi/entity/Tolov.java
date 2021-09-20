package uz.jjp.O.quv.markazi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tolov {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Sessiya sessiya;
    private int qiymat;
    @DateTimeFormat(pattern = "dd-MM-yyyy', 'HH:mm")
    private LocalDateTime tolovVaqt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Sessiya getSessiya() {
        return sessiya;
    }

    public void setSessiya(Sessiya sessiya) {
        this.sessiya = sessiya;
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
}
