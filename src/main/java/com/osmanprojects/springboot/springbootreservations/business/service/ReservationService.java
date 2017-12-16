package com.osmanprojects.springboot.springbootreservations.business.service;

import com.osmanprojects.springboot.springbootreservations.business.domain.RoomReservation;
import com.osmanprojects.springboot.springbootreservations.data.entity.Guest;
import com.osmanprojects.springboot.springbootreservations.data.entity.Reservation;
import com.osmanprojects.springboot.springbootreservations.data.entity.Room;
import com.osmanprojects.springboot.springbootreservations.data.repository.GuestRepository;
import com.osmanprojects.springboot.springbootreservations.data.repository.ReservationRepository;
import com.osmanprojects.springboot.springbootreservations.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by MohamedOsman on 2017-12-16.
 *
 */
@Service
public class ReservationService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation>  roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(),roomReservation);

        });

        List<Reservation> roomReservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if (roomReservations != null){
            roomReservations.forEach(roomReservation -> {
                Guest guest  = this.guestRepository.findOne(roomReservation.getGuestId());
                if(guest != null){
                    RoomReservation roomReservation1 = roomReservationMap.get(roomReservation.getGuestId());
                    roomReservation1.setDate(date);
                    roomReservation1.setFirstName(guest.getFirstName());
                    roomReservation1.setLastName(guest.getLastName());
                    roomReservation1.setGuestId(guest.getId());

                }



            });
        }

        List<RoomReservation> roomReservationss = new ArrayList<>();
        for(Long RoomID:roomReservationMap.keySet()){
            roomReservationss.add(roomReservationMap.get(RoomID));
        }

        return  roomReservationss;


    }




}
