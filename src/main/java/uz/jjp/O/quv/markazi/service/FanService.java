package uz.jjp.O.quv.markazi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.repository.FanRepository;
import uz.jjp.O.quv.markazi.repository.OquvchiRepository;

import java.util.List;

@Service
public class FanService {
@Autowired
FanRepository fanRepository;
public  List<Fan> getAll(){
    return fanRepository.findAll();
}
public void create(Fan o){
    fanRepository.save(o);
}
public void delete(Long id){
    fanRepository.deleteById(id);
}
public void update(Fan o){
    fanRepository.save(o);
}
public Fan getById(Long id){
    return fanRepository.getOne(id);
}
}
