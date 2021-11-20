package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Tolov;
import uz.jjp.O.quv.markazi.repository.TolovRepository;
import uz.jjp.O.quv.markazi.service.TolovService;

import java.util.Arrays;
import java.util.List;

@Service
public class TolovServiceImpl implements TolovService {

    @Autowired
    TolovRepository tolovRepository;
    @Override
    public List<Tolov> getAll() {
        return tolovRepository.findAllByOrderByIdDesc();
    }

    @Override
    public void create(Tolov o) {
        tolovRepository.save(o);
    }

    @Override
    public void delete(Long id) {
        tolovRepository.deleteById(id);
    }

    @Override
    public void update(Tolov o) {
        tolovRepository.save(o);
    }

    @Override
    public Tolov getById(Long id) {
        return tolovRepository.getOne(id);
    }
}
