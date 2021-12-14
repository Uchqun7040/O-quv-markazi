package uz.jjp.O.quv.markazi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jjp.O.quv.markazi.entity.Tolov;
import uz.jjp.O.quv.markazi.repository.TolovRepository;
import uz.jjp.O.quv.markazi.service.TolovService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TolovServiceImpl implements TolovService {

    @Autowired
    TolovRepository tolovRepository;

    @Override
    public Page<Tolov> getAll(Pageable pageable) {
        return tolovRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public List<Tolov> getAll() {
        return tolovRepository.findAllByOrderByIdDesc();
    }

    @Override
    public void create(Tolov o) {

        LocalDate dt=LocalDate.now();
        o.setTolovVaqt(dt);
        if (tolovRepository.findBySessiya_IdAndOyAndYil(o.getSessiya().getId(),o.getOy(),o.getYil()) == null){
            tolovRepository.save(o);
        }

    }

    @Override
    public void delete(Long id) {
        tolovRepository.deleteById(id);
    }

    @Override
    public Page<Tolov> izla(String s, Pageable pageable) {
        return null;
    }

    @Override
    public void update(Tolov o) {
        tolovRepository.save(o);
    }

    @Override
    public Tolov getById(Long id) {
        return tolovRepository.getOne(id);
    }

    @Override
    public Page<Tolov> getAllBySessiyaId(Long id,Pageable pageable) {
        return tolovRepository.getAllBySessiya_IdOrderByIdDesc(id,pageable);
    }

    @Override
    public Page<Tolov> getAllByOquvchiId(Long id,Pageable pageable) {
        return tolovRepository.getAllBySessiya_Oquvchi_IdOrderByIdDesc(id,pageable);
    }

    @Override
    public Page<Tolov> getAllByGuruhId(Long id,Pageable pageable) {
        return tolovRepository.getAllBySessiya_Guruh_IdOrderByIdDesc(id,pageable);
    }
}
