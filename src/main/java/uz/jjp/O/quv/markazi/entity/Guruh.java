package uz.jjp.O.quv.markazi.entity;

import javax.persistence.*;

@Entity
public class Guruh {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String nom;
    @ManyToOne
    private Oqituvchi oqituvchi;
    @ManyToOne
    private Fan fan;
    private int oquvchiSon;
    private int narx;
    private String info;

    public int getOquvchiSon() {
        return oquvchiSon;
    }

    public void setOquvchiSon(int oquvchiSon) {
        this.oquvchiSon = oquvchiSon;
    }

    public Oqituvchi getOqituvchi() {
        return oqituvchi;
    }

    public void setOqituvchi(Oqituvchi oqituvchi) {
        this.oqituvchi = oqituvchi;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNarx() {
        return narx;
    }

    public void setNarx(int narx) {
        this.narx = narx;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
