package zrw.provider.sleuth.trace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zrw.provider.sleuth.trace.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

}
