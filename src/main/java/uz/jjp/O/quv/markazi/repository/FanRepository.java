package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Fan;

import java.util.List;

@Repository
public interface FanRepository extends JpaRepository<Fan,Long> {
    List<Fan> findAllByNomContainsIgnoreCaseOrInfoContainsIgnoreCaseOrId(String nom, String info,Long id);
}
