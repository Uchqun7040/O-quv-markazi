package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.entity.Tolov;

import java.util.List;

@Repository
public interface SessiyaRepository extends JpaRepository<Sessiya,Long> {
List<Sessiya> findAllByOrderByIdDesc();
 List<Sessiya> getAllByOquvchiIdAndAktivIsTrue(Long id);
 List<Sessiya> getAllByGuruhId(Long id);
 List<Sessiya> getAllByGuruhIdAndAktivIsTrue(Long id);
 List<Sessiya> findAllByIdOrGuruh_NomContainsIgnoreCaseOrGuruh_Fan_NomContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrInfoContainsIgnoreCase(Long id,String gnom,String gfnom,String oqfam,Long oid,String oqism,String info);
}
