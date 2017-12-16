package com.osmanprojects.springboot.springbootreservations.data.repository;

import com.osmanprojects.springboot.springbootreservations.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MohamedOsman on 2017-12-16.
 */
@Repository
public interface RoomRepository  extends CrudRepository<Room,Long>{
    Room findByNumber(String number);

}
