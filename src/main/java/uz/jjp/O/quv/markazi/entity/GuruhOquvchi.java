package uz.jjp.O.quv.markazi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class GuruhOquvchi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Guruh guruh;
    @ManyToOne
    private Oquvchi oquvchi;
    @DateTimeFormat(pattern = "dd-MM-yyyy', 'HH:mm")
    private LocalDateTime boshVaqt;
    @DateTimeFormat(pattern = "dd-MM-yyyy', 'HH:mm")
    private LocalDateTime tugVaqt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guruh getGuruh() {
        return guruh;
    }

    public void setGuruh(Guruh guruh) {
        this.guruh = guruh;
    }

    public Oquvchi getOquvchi() {
        return oquvchi;
    }

    public void setOquvchi(Oquvchi oquvchi) {
        this.oquvchi = oquvchi;
    }

    public LocalDateTime getBoshVaqt() {
        return boshVaqt;
    }

    public void setBoshVaqt(LocalDateTime boshVaqt) {
        this.boshVaqt = boshVaqt;
    }

    public LocalDateTime getTugVaqt() {
        return tugVaqt;
    }

    public void setTugVaqt(LocalDateTime tugVaqt) {
        this.tugVaqt = tugVaqt;
    }
}
