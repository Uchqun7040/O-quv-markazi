package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.entity.Tolov;

import java.util.List;

@Repository
public interface SessiyaRepository extends JpaRepository<Sessiya,Long> {
Page<Sessiya> findAllByOrderByIdDesc(Pageable pageable);
 List<Sessiya> getAllByOquvchiIdAndAktivIsTrue(Long id);
 List<Sessiya> getAllByGuruhId(Long id);
 Page<Sessiya> getAllByGuruhIdAndAktivIsTrue(Long id,Pageable pageable);
 Page<Sessiya> findAllByIdOrGuruh_NomContainsIgnoreCaseOrGuruh_Fan_NomContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrInfoContainsIgnoreCase(Long id,String gnom,String gfnom,String oqfam,Long oid,String oqism,String info,Pageable pageable);
 Page<Sessiya> findAllByOquvchi_IdOrOquvchi_IsmContainsIgnoreCaseOrOquvchi_FamiliyaContainsIgnoreCaseOrOquvchi_HujjatContainsIgnoreCaseOrOquvchi_SharifContainsIgnoreCaseOrOquvchi_TelNomerContainsIgnoreCaseOrOquvchi_InfoContainsIgnoreCase(Long id, String ism, String fam, String hujjat, String sharif, String teln, String info,Pageable pageable);

 List<Sessiya> getAllByGuruhIdAndAktivIsTrue(Long id);

}
