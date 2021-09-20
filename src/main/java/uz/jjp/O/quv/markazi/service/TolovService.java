package uz.jjp.O.quv.markazi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Tolov;
import uz.jjp.O.quv.markazi.repository.TolovRepository;


import java.util.List;

@Service
public class TolovService {
@Autowired
TolovRepository tolovRepository;
public  List<Tolov> getAll(){
    return tolovRepository.findAll();
}
public void create(Tolov o){
    tolovRepository.save(o);
}
public void delete(Long id){
    tolovRepository.deleteById(id);
}
public void update(Tolov o){
    tolovRepository.save(o);
}
public Tolov getById(Long id){
    return tolovRepository.getOne(id);
}
}
