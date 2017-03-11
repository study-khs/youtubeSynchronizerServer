package study.khs.api.message.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by jaeyoung on 2017. 3. 11..
 */
public interface MessagePagingAndSortingRepository<T,String extends Serializable> extends CrudRepository<T,String> {
    Iterator<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
}
