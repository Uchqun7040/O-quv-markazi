package uz.jjp.O.quv.markazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.repository.OqituvchiRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OqituvchiService {
    @Autowired
    OqituvchiRepository oqituvchiRepository;
    public List<Oqituvchi> getAll(){
        return oqituvchiRepository.findAll();
    }
    public void create(Oqituvchi o){
        oqituvchiRepository.save(o);
    }
    public void delete(Long id){
        oqituvchiRepository.deleteById(id);
    }

    public void update(Oqituvchi o){
        oqituvchiRepository.save(o);
    }
    public Oqituvchi getById(Long id){
        return oqituvchiRepository.getOne(id);
    }
    public List<Oqituvchi> izla(String s){
        ArrayList<Oqituvchi> ss=new ArrayList<>();
        s=s.toLowerCase();
        String k;
        for (Oqituvchi oqituvchi: oqituvchiRepository.findAll()) {
            k="";
            k+=oqituvchi.getId().toString().toLowerCase();
            k+=oqituvchi.getIsm().toLowerCase();
            k+=oqituvchi.getFamiliya().toLowerCase();
            k+=oqituvchi.getHujjat().toLowerCase();
            k+=oqituvchi.getSharif().toLowerCase();
            k+=oqituvchi.getTel_nomer();
            k+=oqituvchi.getInfo().toLowerCase();
            if (k.contains(s)){
                ss.add(oqituvchi);
            }
        }
        return ss;
    }
}
