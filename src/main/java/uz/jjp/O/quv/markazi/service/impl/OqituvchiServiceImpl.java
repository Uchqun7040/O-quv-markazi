package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.repository.GuruhRepository;
import uz.jjp.O.quv.markazi.repository.OqituvchiRepository;
import uz.jjp.O.quv.markazi.service.OqituvchiService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OqituvchiServiceImpl implements OqituvchiService {
    @Autowired
    OqituvchiRepository oqituvchiRepository;

    @Autowired
    GuruhRepository guruhRepository;

    @Override
    public Page<Oqituvchi> getAll(Pageable pageable) {
        return oqituvchiRepository.findAll(pageable);
    }

    @Override
    public List<Oqituvchi> getAll() {

        return oqituvchiRepository.findAllByAktivIsTrueOrderByIdDesc();
    }

    @Override
    public void create(Oqituvchi o) {
        oqituvchiRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        int guruhlarSoni=guruhRepository.findAllByOqituvchi_IdAndAktivIsTrueOrderByIdDesc(id).size();
        if(guruhlarSoni == 0) {
            Oqituvchi oq = oqituvchiRepository.getOne(id);
            oq.setAktiv(false);
            oqituvchiRepository.save(oq);
        }
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
    public Page<Oqituvchi> izla(String s,Pageable pageable) {
        try{
            Long n=Long.parseLong(s);
            return oqituvchiRepository.findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase(n,s,s,s,s,s,s,pageable);
        }
        catch (Exception x) {

            return oqituvchiRepository.findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase((long)-1,s,s,s,s,s,s,pageable);
        }
    }
}
