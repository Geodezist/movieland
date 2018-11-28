package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.entity.User;

public interface UserDao {
    User getById(int userId);
}
