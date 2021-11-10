package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;

import java.util.List;

@Repository
public interface OqituvchiRepository extends JpaRepository<Oqituvchi,Long> {
    List<Oqituvchi> findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase(Long id,String ism,String fam,String hujjat,String sharif,String teln,String info);
}
