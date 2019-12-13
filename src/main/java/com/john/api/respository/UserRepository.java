package com.john.api.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.john.api.model.UserInf;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserInf, Long>{

	public List<UserInf> findAll();
	
	public UserInf findByUserName(String username);
}
 