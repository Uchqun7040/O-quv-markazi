package uz.jjp.O.quv.markazi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.repository.FanRepository;
import uz.jjp.O.quv.markazi.repository.GuruhRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuruhService {
@Autowired
    GuruhRepository guruhRepository;
public  List<Guruh> getAll(){
    return guruhRepository.findAll();
}
public void create(Guruh o){
    guruhRepository.save(o);
}
public void delete(Long id){
    guruhRepository.deleteById(id);
}
public void update(Guruh o){
    guruhRepository.save(o);
}
public Guruh getById(Long id){
    return guruhRepository.getOne(id);
}
public void guruhlash(Long id){
    Guruh g=getById(id);
    int n=g.getOquvchiSon();
    n++;
    g.setOquvchiSon(n);
    update(g);
}public void unguruhlash(Long id){
    Guruh g=getById(id);
    int n=g.getOquvchiSon();
    n--;
    g.setOquvchiSon(n);
    update(g);
}
    public List<Guruh> izla(String s){
        ArrayList<Guruh> ss=new ArrayList<>();
        s=s.toLowerCase();
        String k;
        for (Guruh guruh: guruhRepository.findAll()) {
            k="";
            k+=guruh.getId().toString().toLowerCase();
            k+=guruh.getNom().toLowerCase();
            k+=guruh.getOqituvchi().getFamiliya().toLowerCase();
            k+=guruh.getOqituvchi().getIsm().toLowerCase();
            k+=guruh.getFan().getNom().toLowerCase();
            k+=guruh.getOquvchiSon();
            k+=guruh.getInfo().toLowerCase();
            if (k.contains(s)){
                ss.add(guruh);
            }
        }
        return ss;
    }
}
