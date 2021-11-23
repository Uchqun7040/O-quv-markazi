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
        return oqituvchiRepository.findAllByOrderByIdDesc();
    }

    @Override
    public void create(Oqituvchi o) {
        oqituvchiRepository.save(o);
    }

    @Override
    public void delete(Long id) {
//        oqituvchiRepository.deleteById(id);
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
        try{
            Long n=Long.parseLong(s);
            return oqituvchiRepository.findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase(n,s,s,s,s,s,s);
        }
        catch (Exception x) {

            return oqituvchiRepository.findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase((long)-1,s,s,s,s,s,s);
        }
    }
}
