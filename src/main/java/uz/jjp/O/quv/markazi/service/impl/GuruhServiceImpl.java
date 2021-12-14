package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.GuruhRepository;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import java.util.List;

@Service
public class GuruhServiceImpl implements GuruhService {
    @Autowired
    GuruhRepository guruhRepository;

    @Autowired
    SessiyaService sessiyaService;
    @Override
    public Page<Guruh> getAll(Pageable pageable) {
        return guruhRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public List<Guruh> getAll() {
        return guruhRepository.findAllByAktiv(true);
    }

    @Override
    public void create(Guruh o) {
        guruhRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        sessiyaService.deleteAllByGuruhId(id);
        Guruh g=guruhRepository.getOne(id);
        g.setAktiv(false);
        guruhRepository.save(g);
    }

    @Override
    public void update(Guruh o) {

        if(!o.getAktiv()) sessiyaService.deleteAllByGuruhId(o.getId());
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
    public Page<Guruh> izla(String s,Pageable pageable) {

        try{
            long n=Long.parseLong(s);
            return guruhRepository.findAllByIdOrNomContainsIgnoreCaseOrOqituvchi_IsmContainsIgnoreCaseOrOqituvchi_FamiliyaContainsIgnoreCaseOrFan_NomContainsIgnoreCaseOrOquvchiSonOrNarxOrInfoContainsIgnoreCaseOrderByIdDesc(n,s,s,s,s,(int)n,(int)n,s,pageable);
        }
        catch (Exception x) {
            int n=-1;
            return guruhRepository.findAllByIdOrNomContainsIgnoreCaseOrOqituvchi_IsmContainsIgnoreCaseOrOqituvchi_FamiliyaContainsIgnoreCaseOrFan_NomContainsIgnoreCaseOrOquvchiSonOrNarxOrInfoContainsIgnoreCaseOrderByIdDesc((long)n,s,s,s,s,n,n,s,pageable);
        }
    }

    @Override
    public List<Guruh> getAllByNotOquvchiId(Long id) {
        List<Guruh> gs=guruhRepository.findAllByAktiv(true);
        List<Sessiya> ss = sessiyaService.getAllByOquvchiId(id);
        ss.forEach(s ->{
            gs.removeIf(g ->(s.getGuruh() == g && s.getAktiv()));
        });
        return gs;
    }

    @Override
    public List<Guruh> getAllByOqituvchi(Long id) {
        return guruhRepository.findAllByOqituvchi_IdAndAktivIsTrueOrderByIdDesc(id);
    }

}
