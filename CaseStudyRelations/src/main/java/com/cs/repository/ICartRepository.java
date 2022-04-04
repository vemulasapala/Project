package com.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cs.bean.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer>{

}