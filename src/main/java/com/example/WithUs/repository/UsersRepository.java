package com.example.WithUs.repository;

import com.example.WithUs.model.Users;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

    Boolean existsUsersByKakaoId(String kakaoId);
}

