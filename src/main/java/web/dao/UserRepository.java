package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    List<User> listUsers();
//    void add(User user);
//    void delete(User user);
//    void edit(User user);
//    User getById(Long id);
    User findByUsername(String username);

}
