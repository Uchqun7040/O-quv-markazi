package uz.jjp.O.quv.markazi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.repository.SessiyaRepository;


import java.util.List;

@Service
public class SessiyaService {
@Autowired
SessiyaRepository sessiyaRepository;
public  List<Sessiya> getAll(){
    return sessiyaRepository.findAll();
}
public void create(Sessiya o){
    sessiyaRepository.save(o);
}
public void delete(Long id){
    sessiyaRepository.deleteById(id);
}
public void update(Sessiya o){
    sessiyaRepository.save(o);
}
public Sessiya getById(Long id){
    return sessiyaRepository.getOne(id);
}
}
