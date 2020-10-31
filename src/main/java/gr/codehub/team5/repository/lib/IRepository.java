package gr.codehub.team5.repository.lib;

import java.util.List;
import java.util.Optional;

/**
 * findById -> Searching with Hibernate for Optional based on Id
 * findAll -> Searching with Hibernate getting back a list Of objects
 * findAll -> Saving an object with Hibernate in our DB
 * deleteById -> Delete an object with Hibernate from our DB based on Id
 * @param <T>
 * @param <K>
 */
public interface IRepository<T, K> {
    Optional<T> findById(K id) ;
    List<T> findAll() ;
    Optional<T> save(T t) ;
    boolean deleteById(K id);

}
