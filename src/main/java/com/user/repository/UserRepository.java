package com.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.entities.Status;
import com.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// get all users
	List<User> findAll();

	// search
	@Query("SELECT u FROM User u  WHERE u.name LIKE '%?%' OR u.city LIKE '%?1%'")
	List<User> searchByKeyword(String keyword);

	// get single user or delete user
	User findAllById(Integer userId);

	@Query("SELECT c FROM User c  WHERE (:name is null or c.name = :name) AND (:city is null or c.city = :city) AND (:status is null or c.status = :status)")
	List<User> findByNameContainingAndCityAndStatus(String name, String city, Status status);

	@Query(nativeQuery = true, value = "SELECT pg_backend_pid()")
	int findPidByPgBackendPid();

	@Query(nativeQuery = true, value = "SELECT pid FROM pg_stat_activity where datname = 'book'")
	int findPidByPgStatActivity();

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "SELECT pg_terminate_backend(:pid)")
	void deleteByPid(Integer pid);

}
