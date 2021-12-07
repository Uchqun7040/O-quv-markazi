package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.repository.FanRepository;
import uz.jjp.O.quv.markazi.repository.GuruhRepository;
import uz.jjp.O.quv.markazi.service.FanService;

import java.util.ArrayList;
import java.util.List;
@Service
public class FanServiceImpl implements FanService {
    @Autowired
    FanRepository fanRepository;
    @Autowired
    GuruhRepository guruhRepository;
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
        if (guruhRepository.findByFan_Nom(getById(id).getNom()) == null){
            fanRepository.deleteById(id);
        }

    }

    @Override
    public void update(Fan o) {
        fanRepository.save(o);
    }

    @Override
    public Fan getById(Long id) {
        return fanRepository.getOne(id);
    }

    @Override
    public List<Fan> izla(String s) {

        try{
            Long n=Long.parseLong(s);
            return fanRepository.findAllByNomContainsIgnoreCaseOrInfoContainsIgnoreCaseOrId(s, s,n);
        }
        catch (Exception x) {

            return fanRepository.findAllByNomContainsIgnoreCaseOrInfoContainsIgnoreCaseOrId(s, s,(long) -1);
        }
    }


    @Override
    public Page<Fan> findPagination(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        return this.fanRepository.findAll(pageable);
    }
}
