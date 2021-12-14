package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;

import java.util.List;

@Repository
public interface OqituvchiRepository extends JpaRepository<Oqituvchi,Long> {
    Page<Oqituvchi> findAllByOrderByIdDesc(Pageable pageable);
    Page<Oqituvchi> findAllByIdOrIsmContainsIgnoreCaseOrFamiliyaContainsIgnoreCaseOrHujjatContainsIgnoreCaseOrSharifContainsIgnoreCaseOrTelNomerContainsIgnoreCaseOrInfoContainsIgnoreCase(Long id,String ism,String fam,String hujjat,String sharif,String teln,String info,Pageable pageable);
    List<Oqituvchi> findAllByAktivIsTrueOrderByIdDesc();

}
