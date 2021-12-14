package uz.jjp.O.quv.markazi.service;

import org.springframework.data.domain.Pageable;
import uz.jjp.O.quv.markazi.entity.Guruh;

import java.util.List;


public interface GuruhService extends GeneralService<Guruh,Long>{


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
     * Bu metod kirib kelayotgan o'quvchi kiritilmagan guruhlarni olib beradi!
     * @param id
     * @return
     */
public List<Guruh> getAllByNotOquvchiId(Long id);

    /**
     * Bu metod oqituvchiga tegishli guruhlarni olib beradi!
     * @param id
     * @return
     */
    List<Guruh> getAllByOqituvchi(Long id);
}
