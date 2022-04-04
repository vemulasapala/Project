package com.cs.bean;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart 
{
	@Id
	@GeneratedValue
	private int cartId;
	private double cartCost;
	
	/*
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    	@JoinTable
  	(
		name = "cart_garden_decor", 
		joinColumns = { @JoinColumn(name = "cart_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "garden_decor_id") }
	)
	private List<GardenDecor> gardenDecor;
	
	/*@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    	@JoinTable
   	(
		name = "cart_seed", 
		joinColumns = { @JoinColumn(name = "cart_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "seed_id") }
	)
	private List<Seed> seed;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    	@JoinTable
    	(
		name = "cart_plant", 
		joinColumns = { @JoinColumn(name = "cart_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "plant_id") }
	)
	private List<Plant> plant;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    	@JoinTable
    	(
		name = "cart_planter", 
		joinColumns = { @JoinColumn(name = "cart_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "planter_id") }
	)
	private List<Planter> planter;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
   	@JoinTable
    	(
		name = "cart_fertilizer", 
		joinColumns = { @JoinColumn(name = "cart_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "fertilizer_id") }
	)
	private List<Fertilizer> fertilizer;*/
}
