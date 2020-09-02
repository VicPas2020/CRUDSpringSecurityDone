package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{
    private static Map<Integer, User> users = new HashMap<>();

    static {
        User user1 = new User();
        user1.setId(1);
        user1.setName("Kirill");
        user1.setSurname("Lebedev");
        user1.setAge(21);
        users.put(user1.getId(), user1);
        User user2 = new User();
        user2.setId(2);
        user2.setName("Max");
        user2.setSurname("Lebedev");
        user2.setAge(20);
        users.put(user2.getId(), user2);
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void add(User user) {
        user.setId(3);
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public void edit(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getById(int id) {
        return users.get(id);
    }
}
