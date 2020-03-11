package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Shuho;
import com.example.demo.entity.User;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface ShuhoRepository extends JpaRepository<Shuho, Integer> {

	List<Shuho> findByUser(User user);

	List<Shuho> findAllByOrderByPostedDayDesc();

	List<Shuho> findByUserAndTaishoWeek(User user, String taisyo);

	List<Shuho> findByUserOrderByPostedDayDesc(User user);
	
	@Query("SELECT s FROM Shuho s GROUP BY taishoWeek")
	public List<Shuho> findAllGropuByTaishoWeek();
	
	List<Shuho> findByTaishoWeek(String taishoWeek);
	
	

}