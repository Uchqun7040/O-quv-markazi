package uz.jjp.O.quv.markazi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sessiya {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Guruh guruh;
    @ManyToOne
    private Oquvchi oquvchi;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime boshVaqt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tugVaqt;
    private boolean tolov;
    private String info;

    public boolean isTolov() {
        return tolov;
    }

    public void setTolov(boolean tolov) {
        this.tolov = tolov;
    }

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
