package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Shuho;
import com.example.demo.entity.User;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface ShuhoRepository extends JpaRepository<Shuho, Integer> {}