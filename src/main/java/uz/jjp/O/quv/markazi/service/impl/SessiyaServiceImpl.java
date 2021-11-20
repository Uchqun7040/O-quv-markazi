package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.SessiyaRepository;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessiyaServiceImpl implements SessiyaService {
    @Autowired
    SessiyaRepository sessiyaRepository;
    @Autowired
    GuruhService guruhService;
    @Override
    public List<Sessiya> getAll() {
        return sessiyaRepository.findAllByOrderByIdDesc();
    }

//    @Override
//    public List<Sessiya> tolovUchun(boolean t) {
//        ArrayList<Sessiya> list=new ArrayList<>();
//        if (t) {
//            for (Sessiya s : sessiyaRepository.findAll()) {
//                if (s.isTolov()) list.add(s);
//            }
//        }
//        else
//            for (Sessiya s : sessiyaRepository.findAll()) {
//                if (!s.isTolov()) list.add(s);
//            }
//        return list;
//    }

    @Override
    public void create(Sessiya o) {
        sessiyaRepository.save(o);
        if (o.getAktiv()) guruhService.guruhlash(o.getGuruh().getId());
    }

    @Override
    public void delete(Long id) {
        guruhService.unguruhlash(sessiyaRepository.getById(id).getGuruh().getId());
        Sessiya s=sessiyaRepository.getOne(id);
        s.setAktiv(false);
        sessiyaRepository.save(s);
    }

    @Override
    public void update(Sessiya o) {
        Long id=o.getId();
        if(!o.getGuruh().getAktiv()) o.setAktiv(false);
        else {
            if (sessiyaRepository.getOne(id).getAktiv()) {
                guruhService.unguruhlash(o.getGuruh().getId());
            }
            if (o.getAktiv()) {
                guruhService.guruhlash(o.getGuruh().getId());

            }
        }
        sessiyaRepository.save(o);
    }

    @Override
    public Sessiya getById(Long id) {
        return sessiyaRepository.getOne(id);
    }

    @Override
    public List<Sessiya> getAllByOquvchiId(Long id) {
        return sessiyaRepository.getAllByOquvchiIdAndAktivIsTrue(id);
    }

    @Override
    public List<Sessiya> izla(String s) {
        try{
            Long n=Long.parseLong(s);
            return sessiyaRepository.findAllByIdOrGuruh_NomContainsIgnoreCaseOrGuruh_Fan_NomContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrInfoContainsIgnoreCase(n,s,s,s,n,s,s);
        }
        catch (Exception x) {
            return sessiyaRepository.findAllByIdOrGuruh_NomContainsIgnoreCaseOrGuruh_Fan_NomContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrInfoContainsIgnoreCase((long)-1,s,s,s,(long)-1,s,s);
        }
    }

    @Override
    public void deleteByOquvchiId(Long id) {
        List<Sessiya> ss=sessiyaRepository.getAllByOquvchiIdAndAktivIsTrue(id);
        for (Sessiya s: ss) {
            delete(s.getId());
        }

    }

    @Override
    public void deleteAllByGuruhId(Long id) {
        List<Sessiya> ss=sessiyaRepository.getAllByGuruhIdAndAktivIsTrue(id);
        for (Sessiya s: ss) {
            delete(s.getId());
        }
    }

    @Override
    public List<Sessiya> getAllByGuruhId(Long id) {
        return sessiyaRepository.getAllByGuruhIdAndAktivIsTrue(id);
    }


}
