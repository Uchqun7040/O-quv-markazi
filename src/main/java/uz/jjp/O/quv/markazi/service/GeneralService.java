package uz.jjp.O.quv.markazi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralService<Entity, IdType> {
    /**
     * @return All Entity
     * */
    Page<Entity> getAll(Pageable pageable);
    List<Entity> getAll();
    Entity getById(IdType id);
    void create(Entity entity);
    void update(Entity entity);
    void delete(IdType id);
    Page<Entity> izla(String s, Pageable pageable);
}