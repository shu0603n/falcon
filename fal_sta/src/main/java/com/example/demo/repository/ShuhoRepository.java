package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Shuho;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface ShuhoRepository extends JpaRepository<Shuho, Integer> {}