package uz.jjp.O.quv.markazi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import java.util.List;


public interface SessiyaService extends GeneralService<Sessiya,Long>{

    /**
     * Bu metod kirayotgan boolean argumentga qarab to'lov qilingan yoki qilinmagan O'quvchilar ro'yxatini qaytaradi!
     * @param t
     * @return
     */
    public Page<Sessiya> tolovUchun(boolean t, Pageable pageable);

    /**
     * Bu metod kirayotgan id li o'quvchi tayinlangan guruhlashlarni ro'yxatini qaytaradi!
     * @param id
     * @return
     */
    public List<Sessiya> getAllByOquvchiId(Long id);



    /**
     * Bu metod o'quvchi o'chirilganda avtomatik ravishda, unga tegishli sessiyalar ham o'chirilishini bajaradi.
     * @param id
     */
    void deleteByOquvchiId(Long id);

    /**
     * Bu metod guruh o'chirilganda avtomatik ravishda, unga tegishli sessiyalar ham o'chirilishini bajaradi.
     * @param id
     */
    void deleteAllByGuruhId(Long id);

    /**
     * Bu metod Guruhga tegishli sessiyalarni olib beradi
     * @param id
     * @return
     */
    Page<Sessiya> getAllByGuruhId(Long id,Pageable pageable);

    /**
     * Bu metod Guruhga tegishli sessiyalarni saralab olib beradi
     * @return
     */
    Page<Sessiya> izlaOnGuruh(Long guruhId, String satr, Pageable pageable);
}
