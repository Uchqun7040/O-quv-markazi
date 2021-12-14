package uz.jjp.O.quv.markazi.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.jjp.O.quv.markazi.entity.Tolov;



import java.util.List;


public interface TolovService extends GeneralService<Tolov,Long>{


    /**
     * Bu metod sessiyaga tegishli tolovlarni olib beradi
     * @param id
     * @return
     */
    public Page<Tolov> getAllBySessiyaId(Long id, Pageable pageable);

    /**
     * Bu metod oquvchiga tegishli tolovlarni olib beradi
     * @param id
     * @return
     */
    public Page<Tolov> getAllByOquvchiId(Long id,Pageable pageable);

    /**
     * Bu metod guruhga tegishli tolovlarni olib beradi
     * @param id
     * @return
     */
    Page<Tolov> getAllByGuruhId(Long id,Pageable pageable);
}
