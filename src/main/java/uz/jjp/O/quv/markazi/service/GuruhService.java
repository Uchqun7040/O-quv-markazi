package uz.jjp.O.quv.markazi.service;

import uz.jjp.O.quv.markazi.entity.Guruh;
import java.util.List;


public interface GuruhService {
    /**
     * Bu metod ro'yxatdagi barcha Guruhlarni bazadan olib uzatadi.
     *
     */
public  List<Guruh> getAll();

    /**
     *Bu metod kirib kelayotgan yangi Guruh obyektini bazaga qo'shib qo'yadi!
     * @param o
     */
public void create(Guruh o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan Guruh obyektini bazadan o'chirib yuboradi.
     * @param id
     */
public void delete(Long id);

    /**
     * Bu metod bazadagi mavjud obyekt ma'lumotlarini kirib kelayotgan yangi Guruh obyektiga almashtiradi!
     * @param o
     */
public void update(Guruh o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan Guruh obyektini bazadan olib beradi.
     * @param id
     */
public Guruh getById(Long id);

    /**
     * Bu metod o'quvchini biron guruhga tayinlanganda shu guruhning 'oquvchilarSoni' xususiyatini birga oshiradi.
     * @param id
     */
    public void guruhlash(Long id);

    /**
     * Bu metod o'quvchini biron guruhga tayinlanganda shu guruhning 'oquvchilarSoni' xususiyatini birga kamaytiradi.
     * @param id
     */
public void unguruhlash(Long id);



    /**
     * Bu metod kirib kelayotgan String turidagi ma'lumot bo'yicha saralab bazadan obyektlar jamlanmasini olib beradi!
     * @param s
     * @return
     */
public List<Guruh> izla(String s);

     /**
     * Bu metod kirib kelayotgan o'quvchi kiritilmagan guruhlarni olib beradi!
     * @param id
     * @return
     */
public List<Guruh> getAllByNotOquvchiId(Long id);


}
