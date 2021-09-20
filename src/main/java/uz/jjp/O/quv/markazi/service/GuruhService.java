package uz.jjp.O.quv.markazi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.repository.FanRepository;
import uz.jjp.O.quv.markazi.repository.GuruhRepository;

import java.util.List;

@Service
public class GuruhService {
@Autowired
    GuruhRepository guruhRepository;
public  List<Guruh> getAll(){
    return guruhRepository.findAll();
}
public void create(Guruh o){
    guruhRepository.save(o);
}
public void delete(Long id){
    guruhRepository.deleteById(id);
}
public void update(Guruh o){
    guruhRepository.save(o);
}
public Guruh getById(Long id){
    return guruhRepository.getOne(id);
}
}
