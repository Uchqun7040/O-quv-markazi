package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.Oquvchi;

import java.util.List;


public interface OquvchiService {
    /**
     * Bu metod ro'yxatdagi barcha O'quvchilarni bazadan olib uzatadi.
     *
     */
public  List<Oquvchi> getAll();

    /**
     *Bu metod kirib kelayotgan yangi O'quvchi obyektini bazaga qo'shib qo'yadi!
     * @param o
     */
public void create(Oquvchi o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan O'quvchi obyektini bazadan o'chirib yuboradi.
     * @param id
     */
public void delete(Long id);

    /**
     * Bu metod bazadagi mavjud obyekt ma'lumotlarini kirib kelayotgan yangi O'quvchi obyektiga almashtiradi!
     * @param o
     */
public void update(Oquvchi o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan O'quvchi obyektini bazadan olib beradi.
     * @param id
     */
public Oquvchi getById(Long id);

    /**
     * Bu metod kirib kelayotgan String turidagi ma'lumot bo'yicha saralab bazadan obyektlar jamlanmasini olib beradi!
     * @param s
     * @return
     */
public List<Oquvchi> izla(String s);
    /**
     * Bu metod kirib kelayotgan Long turidagi id li guruhga tegishli oquvchilarni(aktivlarini) olib beradi.
     * @param id
     */
public List<Oquvchi> getAllByGuruhId(Long id);
}
