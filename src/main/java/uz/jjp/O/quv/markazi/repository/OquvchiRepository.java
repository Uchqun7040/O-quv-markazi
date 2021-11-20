package uz.jjp.O.quv.markazi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;

import java.util.List;

@Repository
public interface OquvchiRepository extends JpaRepository<Oquvchi, Long> {
    List<Oquvchi> findAllByOrderByIdDesc();
    List<Oquvchi> findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase(Long id, String ism, String fam, String hujjat, String sharif, String teln, String info);
}
