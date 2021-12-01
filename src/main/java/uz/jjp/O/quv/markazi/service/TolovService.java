package uz.jjp.O.quv.markazi.service;


import uz.jjp.O.quv.markazi.entity.Tolov;



import java.util.List;


public interface TolovService {
    /**
     * Bu metod ro'yxatdagi barcha To'lovlarni bazadan olib uzatadi.
     *
     */
public  List<Tolov> getAll();
    /**
     *Bu metod kirib kelayotgan yangi To'lov obyektini bazaga qo'shib qo'yadi!
     * @param o
     */
public void create(Tolov o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan To'lov obyektini bazadan o'chirib yuboradi.
     * @param id
     */
public void delete(Long id);

    /**
     * Bu metod bazadagi mavjud obyekt ma'lumotlarini kirib kelayotgan yangi Tolov obyektiga almashtiradi!
     * @param o
     */
public void update(Tolov o);

    /**
     * Bu metod bazadagi ID si kirib kelayotgan Long turidagi id songa teng bo'lgan To'lov obyektini bazadan olib beradi.
     * @param id
     */
public Tolov getById(Long id);

    /**
     * Bu metod sessiyaga tegishli tolovlarni olib beradi
     * @param id
     * @return
     */
    public List<Tolov> getAllBySessiyaId(Long id);

    /**
     * Bu metod oquvchiga tegishli tolovlarni olib beradi
     * @param id
     * @return
     */
    public List<Tolov> getAllByOquvchiId(Long id);

    /**
     * Bu metod guruhga tegishli tolovlarni olib beradi
     * @param id
     * @return
     */
    List<Tolov> getAllByGuruh(Long id);
}
