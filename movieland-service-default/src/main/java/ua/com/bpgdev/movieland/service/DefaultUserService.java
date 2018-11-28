package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.UserDao;
import ua.com.bpgdev.movieland.entity.User;

@Service
public class DefaultUserService implements UserService {
    private UserDao userDao;

    public DefaultUserService(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }
}
