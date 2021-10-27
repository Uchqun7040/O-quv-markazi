package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.GuruhRepository;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuruhServiceImpl implements GuruhService {
    @Autowired
    GuruhRepository guruhRepository;

    @Autowired
    SessiyaService sessiyaService;
    @Override
    public List<Guruh> getAll() {
        return guruhRepository.findAll();
    }

    @Override
    public void create(Guruh o) {
        guruhRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        guruhRepository.deleteById(id);
    }

    @Override
    public void update(Guruh o) {
        guruhRepository.save(o);
    }

    @Override
    public Guruh getById(Long id) {
        return guruhRepository.getOne(id);
    }

    @Override
    public void guruhlash(Long id) {
        Guruh g=getById(id);
        int n=g.getOquvchiSon();
        n++;
        g.setOquvchiSon(n);
        update(g);
    }

    @Override
    public void unguruhlash(Long id) {
        Guruh g=getById(id);
        int n=g.getOquvchiSon();
        n--;
        g.setOquvchiSon(n);
        update(g);
    }

    @Override
    public List<Guruh> izla(String s) {
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
            k+=guruh.getNarx();
            k+=guruh.getInfo().toLowerCase();
            if (k.contains(s)){
                ss.add(guruh);
            }
        }
        return ss;
    }

    @Override
    public List<Guruh> getAllByNotOquvchiId(Long id) {
        List<Guruh> gs=getAll();
        List<Sessiya> ss = sessiyaService.getByOquvchiId(id);
        ss.forEach(s ->{
            gs.removeIf(g ->(s.getGuruh() == g && s.getAktiv()));
        });
        return gs;
    }
}
