package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.OquvchiRepository;
import uz.jjp.O.quv.markazi.repository.SessiyaRepository;
import uz.jjp.O.quv.markazi.service.OquvchiService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OquvchiServiceImpl implements OquvchiService {
    @Autowired
    OquvchiRepository oquvchiRepository;


    @Override
    public Page<Oquvchi> getAll(Pageable pageable) {
        return oquvchiRepository.findAllByOrderByIdDesc(pageable);
    }

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
    public Page<Oquvchi> izla(String s,Pageable pageable) {
        try{
            Long n=Long.parseLong(s);
            return oquvchiRepository.findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase(n,s,s,s,s,s,s,pageable);
        }
        catch (Exception x) {

            return oquvchiRepository.findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase((long)-1,s,s,s,s,s,s,pageable);
        }
    }

}
