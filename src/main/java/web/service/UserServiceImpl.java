package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserRepository;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDao.save(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getOne(id); }

    @Override
    public User findByUserName(String username) { return userDao.findByUsername(username); }
}
