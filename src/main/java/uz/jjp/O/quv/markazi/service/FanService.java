package uz.jjp.O.quv.markazi.service;



import org.springframework.data.domain.Page;
import uz.jjp.O.quv.markazi.entity.Fan;

import java.util.List;


public interface FanService {
    /**
     * Bu metod ro'yxatdagi barcha Fanlarni olib uzatadi!
     */
    public  List<Fan> getAll();

    /**
     *Bu metod kirib kelayotgan yangi Fan obyektini qo'shib qo'yadi!
     * @param o
     */
    public void create(Fan o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan Fan obyektini o'chirib yuboradi.
     * @param id
     */
    public void delete(Long id);

    /**
     * Bu metod mavjud obyekt ma'lumotlarini kirib kelayotgan yangi Fan obyekti ma'lumotlariga almashtiradi!
     * @param o
     */
    public void update(Fan o);

    /**
     * Bu metod ID si kirib kelayotgan Long turidagi id songa teng bo'lgan Fan obyektini bazadan olib beradi.
     * @param id
     */
    public Fan getById(Long id);

    /**
     * Bu metod kirib kelayotgan String turidagi ma'lumot bo'yicha saralab obyektlar jamlanmasini olib beradi!
     * @param s
     * @return
     */
    public List<Fan> izla(String s);

    public Page<Fan> findPagination(int pageNo, int pageSize);
}
