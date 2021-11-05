package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.OquvchiRepository;
import uz.jjp.O.quv.markazi.repository.SessiyaRepository;
import uz.jjp.O.quv.markazi.service.OquvchiService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OquvchiServiceImpl implements OquvchiService {
    @Autowired
    OquvchiRepository oquvchiRepository;
    @Autowired
    SessiyaRepository sessiyaRepository;

    @Override
    public List<Oquvchi> getAll() {
        return oquvchiRepository.findAll();
    }

    @Override
    public void create(Oquvchi o) {
        oquvchiRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        oquvchiRepository.deleteById(id);
    }

    @Override
    public void update(Oquvchi o) {
        oquvchiRepository.save(o);
    }

    @Override
    public Oquvchi getById(Long id) {
        return oquvchiRepository.getOne(id);
    }

    @Override
    public List<Oquvchi> izla(String s) {
        ArrayList<Oquvchi> ss=new ArrayList<>();
        s=s.toLowerCase();
        String k;
        for (Oquvchi oquvchi: oquvchiRepository.findAll()) {
            k="";
            k+=oquvchi.getId();
            k+=oquvchi.getIsm().toLowerCase();
            k+=oquvchi.getFamiliya().toLowerCase();
            k+=oquvchi.getHujjat().toLowerCase();
            k+=oquvchi.getSharif().toLowerCase();
            k+=oquvchi.getTel_nomer();
            k+=oquvchi.getInfo().toLowerCase();
            if (k.contains(s)){
                ss.add(oquvchi);
            }
        }
        return ss;
    }

    @Override
    public List<Oquvchi> getAllByGuruhId(Long id) {
        List<Sessiya> ss=sessiyaRepository.getAllByGuruhId(id);
        List<Oquvchi> oqs=new ArrayList<>();
        for (Sessiya s: ss  ) {
            if (s.getAktiv())
            oqs.add(s.getOquvchi());
        }
        return oqs;
    }
}
