package uz.jjp.O.quv.markazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.repository.OqituvchiRepository;

import java.util.List;

@Service
public class OqituvchiService {
    @Autowired
    OqituvchiRepository oqituvchiRepository;
    public List<Oqituvchi> getAll(){
        return oqituvchiRepository.findAll();
    }
    public void create(Oqituvchi o){
        oqituvchiRepository.save(o);
    }
    public void delete(Long id){
        oqituvchiRepository.deleteById(id);
    }

    public void update(Oqituvchi o){
        oqituvchiRepository.save(o);
    }
    public Oqituvchi getById(Long id){
        return oqituvchiRepository.getOne(id);
    }
}
