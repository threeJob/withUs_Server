package com.example.WithUs.repository;

import com.example.WithUs.model.HashtagToRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagToRoomRepository extends JpaRepository<HashtagToRoom,Long> {





    List<HashtagToRoom> findAllByRoomId(Long roomId);
}
