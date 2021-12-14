package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.entity.Tolov;

import java.util.List;

@Repository
public interface TolovRepository extends JpaRepository<Tolov,Long> {
    List<Tolov> findAllByOrderByIdDesc();
    Page<Tolov> findAllByOrderByIdDesc(Pageable pageble);



    Tolov findBySessiya_IdAndOyAndYil(Long id, String oy, int yil);

    Page<Tolov> getAllBySessiya_IdOrderByIdDesc(Long id,Pageable pageable);
    Page<Tolov> getAllBySessiya_Oquvchi_IdOrderByIdDesc(Long id,Pageable pageable);
    Page<Tolov> getAllBySessiya_Guruh_IdOrderByIdDesc(Long id,Pageable pageable);
}
