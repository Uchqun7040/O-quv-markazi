package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.entity.Tolov;
import uz.jjp.O.quv.markazi.repository.SessiyaRepository;
import uz.jjp.O.quv.markazi.repository.TolovRepository;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessiyaServiceImpl implements SessiyaService {
    @Autowired
    SessiyaRepository sessiyaRepository;

    @Autowired
    TolovRepository tolovRepository;

    @Autowired
    GuruhService guruhService;

    @Override
    public Page<Sessiya> getAll(Pageable pageable) {
        Page<Sessiya> ss=sessiyaRepository.findAllByOrderByIdDesc(pageable);
        for (Sessiya s: ss) {
            s.setTolov(isTolov(s));
        }
        return ss;
    }

    @Override
    public List<Sessiya> getAll() {
        List<Sessiya> ss=sessiyaRepository.findAll();
        for (Sessiya s: ss) {
            s.setTolov(isTolov(s));
        }
        return ss;
    }

    @Override
    public Page<Sessiya> tolovUchun(boolean t,Pageable pageable) {
        Page<Sessiya> ss=getAll(pageable);
        if (t) {

            List<Sessiya> sessiyas = ss.stream().filter(Sessiya::getTolov).collect(Collectors.toList());
            return new PageImpl<>(sessiyas, pageable, ss.getSize());
        }
        else{
            List<Sessiya> sessiyas = ss.stream().filter(sessiya ->!sessiya.getTolov()).collect(Collectors.toList());
            return new PageImpl<>(sessiyas, pageable, ss.getSize());
        }


    }

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
        Sessiya s=sessiyaRepository.getOne(id);
        s.setTolov(isTolov(s));
        return s;
    }

    @Override
    public List<Sessiya> getAllByOquvchiId(Long id) {
        List<Sessiya> ss=sessiyaRepository.getAllByOquvchiIdAndAktivIsTrue(id);
        for (Sessiya s: ss) {
            s.setTolov(isTolov(s));
        }
        return ss;
    }

    @Override
    public Page<Sessiya> izla(String s,Pageable pageable) {

        try{
            Long n=Long.parseLong(s);
            Page<Sessiya> ss=sessiyaRepository.findAllByIdOrGuruh_NomContainsIgnoreCaseOrGuruh_Fan_NomContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrInfoContainsIgnoreCase(n,s,s,s,n,s,s,pageable);
            for (Sessiya se: ss) {
                se.setTolov(isTolov(se));
            }
            return ss;
        }
        catch (Exception x) {
            Page<Sessiya> ss=sessiyaRepository.findAllByIdOrGuruh_NomContainsIgnoreCaseOrGuruh_Fan_NomContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrInfoContainsIgnoreCase((long)-1,s,s,s,(long)-1,s,s,pageable);
            for (Sessiya se: ss) {
                se.setTolov(isTolov(se));
            }
            return ss;
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
    public Page<Sessiya> getAllByGuruhId(Long id,Pageable pageable) {
        Page<Sessiya> ss=sessiyaRepository.getAllByGuruhIdAndAktivIsTrue(id,pageable);
        for (Sessiya s: ss) {
            s.setTolov(isTolov(s));
        }
        return ss;
    }

    @Override
    public Page<Sessiya> izlaOnGuruh(Long guruhId, String s, Pageable pageable) {
        try{
            Long n=Long.parseLong(s);
            return sessiyaRepository.findAllByOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_HujjatContainsIgnoreCaseOrOquvchi_SharifContainsIgnoreCaseOrOquvchi_TelNomerContainsIgnoreCaseOrOquvchi_InfoContainsIgnoreCase(n,s,s,s,s,s,s,pageable);
        }
        catch (Exception x) {

            return sessiyaRepository.findAllByOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_HujjatContainsIgnoreCaseOrOquvchi_SharifContainsIgnoreCaseOrOquvchi_TelNomerContainsIgnoreCaseOrOquvchi_InfoContainsIgnoreCase(-1L,s,s,s,s,s,s,pageable);
        }
    }


    Boolean isTolov(Sessiya sessiya){
        LocalDate vaqt=LocalDate.now();
        String oy="";
        int oy_nomer=vaqt.getMonthValue();
        int yil=vaqt.getYear();
        switch (oy_nomer){
            case 1: oy="Yanvar";break;
            case 2: oy="Fevral";break;
            case 3: oy="Mart";break;
            case 4: oy="Aprel";break;
            case 5: oy="May";break;
            case 6: oy="Iyun";break;
            case 7: oy="Iyul";break;
            case 8: oy="Avgust";break;
            case 9: oy="Sentabr";break;
            case 10: oy="Oktabr";break;
            case 11: oy="Noyabr";break;
            case 12: oy="Dekabr";
        }
        Tolov tolov=tolovRepository.findBySessiya_IdAndOyAndYil(sessiya.getId(),oy,yil);
        return tolov != null;
    }
}
