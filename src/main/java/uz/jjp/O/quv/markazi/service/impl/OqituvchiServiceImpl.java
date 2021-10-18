package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.repository.OqituvchiRepository;
import uz.jjp.O.quv.markazi.service.OqituvchiService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OqituvchiServiceImpl implements OqituvchiService {
    @Autowired
    OqituvchiRepository oqituvchiRepository;
    @Override
    public List<Oqituvchi> getAll() {
        return oqituvchiRepository.findAll();
    }

    @Override
    public void create(Oqituvchi o) {
        oqituvchiRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        oqituvchiRepository.deleteById(id);
    }

    @Override
    public void update(Oqituvchi o) {
        oqituvchiRepository.save(o);
    }

    @Override
    public Oqituvchi getById(Long id) {
        return oqituvchiRepository.getOne(id);
    }

    @Override
    public List<Oqituvchi> izla(String s) {
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
