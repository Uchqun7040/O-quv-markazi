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
        return sessiyaRepository.findAll();
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
        guruhService.guruhlash(o.getGuruh().getId());
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
        sessiyaRepository.save(o);
    }

    @Override
    public Sessiya getById(Long id) {
        return sessiyaRepository.getOne(id);
    }

    @Override
    public List<Sessiya> getByOquvchiId(Long id) {

        return sessiyaRepository.getAllByOquvchiIdAndAktivIsTrue(id);
    }

    @Override
    public List<Sessiya> izla(String s) {
        ArrayList<Sessiya> ss=new ArrayList<>();
        s=s.toLowerCase();
        String k;
        for (Sessiya sessiya: sessiyaRepository.findAll()) {
            k="";
            k+=sessiya.getId().toString().toLowerCase();
            k+=sessiya.getGuruh().getNom().toLowerCase();
            k+=sessiya.getGuruh().getFan().getNom().toLowerCase();
            k+=sessiya.getBoshVaqt();
            k+=sessiya.getTugVaqt();
            k+=sessiya.getOquvchi().getFamiliya().toLowerCase();
            k+=sessiya.getOquvchi().getId().toString().toLowerCase();
            k+=sessiya.getOquvchi().getIsm().toLowerCase();
            if (k.contains(s)){
                ss.add(sessiya);
            }
        }
        return ss;
    }

    @Override
    public void deleteByOquvchiId(Long id) {
        List<Sessiya> ss=sessiyaRepository.getAllByOquvchiIdAndAktivIsTrue(id);
        for (Sessiya s: ss) {
            delete(s.getId());
        }
    }


}
