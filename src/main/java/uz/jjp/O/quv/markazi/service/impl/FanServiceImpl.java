package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.repository.FanRepository;
import uz.jjp.O.quv.markazi.service.FanService;

import java.util.List;
@Service
public class FanServiceImpl implements FanService {
    @Autowired
    FanRepository fanRepository;
    @Override
    public List<Fan> getAll() {
        return fanRepository.findAll();
    }

    @Override
    public void create(Fan o) {
        fanRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        fanRepository.deleteById(id);
    }

    @Override
    public void update(Fan o) {
        fanRepository.save(o);
    }

    @Override
    public Fan getById(Long id) {
        return fanRepository.getOne(id);
    }

//    @Override
//    public List<Fan> izla(String s) {
//        ArrayList<Fan> ss=new ArrayList<>();
//        s=s.toLowerCase();
//        String k;
//        for (Fan fan: fanRepository.findAll()) {
//            k="";
//            k+=fan.getId().toString().toLowerCase();
//            k+=fan.getNom().toLowerCase();
//            k+=fan.getInfo().toLowerCase();
//            if (k.contains(s)){
//                ss.add(fan);
//            }
//        }
//        return ss;
//    }
    @Override
    public List<Fan> izla(String s) {
        return fanRepository.findAllByNomContainsOrInfoContains(s,s);
    }
}
