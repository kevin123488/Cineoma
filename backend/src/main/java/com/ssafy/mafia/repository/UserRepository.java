package com.ssafy.mafia.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ssafy.mafia.entity.User;

@Repository
public class UserRepository {
	@PersistenceContext
	private EntityManager em;
	
	public int save(User user) {
		em.persist(user);
		return user.getNo();
	}
	public User find(String id) {
		return em.find(User.class, id);
	}
	public User checkLogin(String id, String password) {
		User tmp = em.createQuery("select u from user u where u.id = :id and u.password = :password", User.class).setParameter("id", id).getSingleResult();
		return tmp;
	}
}
