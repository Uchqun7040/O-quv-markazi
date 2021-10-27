package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import java.util.List;


public interface SessiyaService {
    /**
     * Bu metod ro'yxatdagi barcha Guruhlashlarni bazadan olib uzatadi.
     *
     */
public  List<Sessiya> getAll();

//    /**
//     * Bu metod kirayotgan boolean argumentga qarab to'lov qilingan yoki qilinmagan O'quvchilar ro'yxatini qaytaradi!
//     * @param t
//     * @return
//     */
//    public List<Sessiya> tolovUchun(boolean t);

    /**
     *Bu metod kirib kelayotgan yangi Guruhlash obyektini bazaga qo'shib qo'yadi!
     * @param o
     */
public void create(Sessiya o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan Guruhlash obyektini bazadan o'chirib yuboradi.
     * @param id
     */
public void delete(Long id);

    /**
     * Bu metod bazadagi mavjud obyekt ma'lumotlarini kirib kelayotgan yangi Guruhlash obyektiga almashtiradi!
     * @param o
     */
public void update(Sessiya o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan Guruhlash obyektini bazadan olib beradi.
     * @param id
     */
public Sessiya getById(Long id);

    /**
     * Bu metod kirayotgan id li o'quvchi tayinlangan guruhlashlarni ro'yxatini qaytaradi!
     * @param id
     * @return
     */
    public List<Sessiya> getByOquvchiId(Long id);

    /**
     * Bu metod kirib kelayotgan String turidagi ma'lumot bo'yicha saralab bazadan obyektlar jamlanmasini olib beradi!
     * @param s
     * @return
     */
public List<Sessiya> izla(String s);

    /**
     * Bu metod o'quvchi o'chirilganda avtomatik ravishda, unga tegishli sessiyalar ham o'chirilishini bajaradi.
     * @param id
     */
    void deleteByOquvchiId(Long id);


}
