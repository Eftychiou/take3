package com.eftichiou.take3.dao;

import java.util.List;

import com.eftichiou.take3.entity.Room;
import com.eftichiou.take3.entity.User;
public interface IRoomDAO {
public Boolean addRoom(Room room);
public Boolean deleteRoom(int id);
public List<Room> findAll();
public User findRoomAdmin(Room room);
}
