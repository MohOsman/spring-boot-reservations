package com.osmanprojects.springboot.springbootreservations.data.repository;

import com.osmanprojects.springboot.springbootreservations.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}