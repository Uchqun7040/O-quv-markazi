package uz.jjp.O.quv.markazi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Oquvchi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ism;
    private String familiya;
    private String sharif;
    private String hujjat;
    private String tel_nomer;
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsm() {
        return ism;
    }

    public void setIsm(String ism) {
        this.ism = ism;
    }

    public String getFamiliya() {
        return familiya;
    }

    public void setFamiliya(String familiya) {
        this.familiya = familiya;
    }

    public String getSharif() {
        return sharif;
    }

    public void setSharif(String sharif) {
        this.sharif = sharif;
    }

    public String getHujjat() {
        return hujjat;
    }

    public void setHujjat(String hujjat) {
        this.hujjat = hujjat;
    }

    public String getTel_nomer() {
        return tel_nomer;
    }

    public void setTel_nomer(String tel_nomer) {
        this.tel_nomer = tel_nomer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
