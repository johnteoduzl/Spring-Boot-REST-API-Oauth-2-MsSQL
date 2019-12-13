package com.john.api.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.john.api.model.User;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long>{

	public List<User> findAll();
}
 