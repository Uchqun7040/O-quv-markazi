package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import java.util.List;


public interface OqituvchiService {
    /**
     * Bu metod ro'yxatdagi barcha O'qituvchilarni bazadan olib uzatadi.
     *
     */
    public List<Oqituvchi> getAll();

    /**
     *Bu metod kirib kelayotgan yangi O'qituvchi obyektini bazaga qo'shib qo'yadi!
     * @param o
     */
    public void create(Oqituvchi o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan O'qituvchi obyektini bazadan o'chirib yuboradi.
     * @param id
     */
    public void delete(Long id);

    /**
     * Bu metod bazadagi mavjud obyekt ma'lumotlarini kirib kelayotgan yangi O'qituvchi obyektiga almashtiradi!
     * @param o
     */
    public void update(Oqituvchi o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan O'qituvchi obyektini bazadan olib beradi.
     * @param id
     */
    public Oqituvchi getById(Long id);

    /**
     * Bu metod kirib kelayotgan String turidagi ma'lumot bo'yicha saralab bazadan obyektlar jamlanmasini olib beradi!
     * @param s
     * @return
     */

    public List<Oqituvchi> izla(String s);
}
