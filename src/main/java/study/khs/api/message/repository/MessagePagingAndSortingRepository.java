package study.khs.api.message.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import study.khs.api.message.domain.Message;

/**
 * Created by jaeyoung on 2017. 3. 11..
 */
public interface MessagePagingAndSortingRepository extends PagingAndSortingRepository<Message,String> {

}
