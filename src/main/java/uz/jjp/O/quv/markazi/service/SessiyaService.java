package uz.jjp.O.quv.markazi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.SessiyaRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class SessiyaService {
@Autowired
SessiyaRepository sessiyaRepository;
public  List<Sessiya> getAll(){
    return sessiyaRepository.findAll();
}
public List<Sessiya> tolovUchun(boolean t){
    ArrayList<Sessiya> list=new ArrayList<>();
    if (t) {
        for (Sessiya s : sessiyaRepository.findAll()) {
            if (s.isTolov()) list.add(s);
        }
    }
    else
        for (Sessiya s : sessiyaRepository.findAll()) {
        if (!s.isTolov()) list.add(s);
        }
    return list;
}

public void create(Sessiya o){
    sessiyaRepository.save(o);
}
public void delete(Long id){
    sessiyaRepository.deleteById(id);
}
public void update(Sessiya o){
    sessiyaRepository.save(o);
}
public Sessiya getById(Long id){
    return sessiyaRepository.getOne(id);
}
public List<Sessiya> getByOquvchiId(Long id){
    ArrayList<Sessiya> ss=new ArrayList<>();
    for (Sessiya s: sessiyaRepository.findAll()){
        if (s.getOquvchi().getId() == id) ss.add(s);
    }
    return ss;
}
public List<Sessiya> izla(String s){
    ArrayList<Sessiya> ss=new ArrayList<>();
    s=s.toLowerCase();
    String k;
    for (Sessiya sessiya: sessiyaRepository.findAll()) {
        k="";
        k+=sessiya.getId().toString().toLowerCase();
        k+=sessiya.getGuruh().getNom().toLowerCase();
        k+=sessiya.getGuruh().getFan().getNom().toLowerCase();
        k+=sessiya.getBoshVaqt();
        k+=sessiya.getTugVaqt();
        k+=sessiya.getOquvchi().getFamiliya().toLowerCase();
        k+=sessiya.getOquvchi().getId().toString().toLowerCase();
        k+=sessiya.getOquvchi().getIsm().toLowerCase();
        if (k.contains(s)){
            ss.add(sessiya);
        }
    }
    return ss;
}
}
