package com.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.repository.ICartRepository;

@Service
public class CartServiceImpl implements ICartService
{
	@Autowired
	ICartRepository cartRepo;
	/*
	@Autowired
	IGardenDecorRepository gardenDecorRepo;
	@Autowired
	ISeedRepository seedRepo;
	@Autowired
	IPlantRepository plantRepo;
	@Autowired
	IPlanterRepository planterRepo;
	@Autowired
	IFertilizerRepository fertilizerRepo;
	
	// view the cart
	@Override
	public Cart viewCart(int cartId) 
	{
		Optional<Cart> cart = cartRepo.findById(cartId);
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		return cart.get();
	}
	
	// add garden decor to cart
	@Override
	public Cart addGardenDecorToCart(int cartId, int gardenDecorId) 
	{
		Optional<GardenDecor> gardenDecor = gardenDecorRepo.findById(gardenDecorId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!gardenDecor.isPresent())
		{
			throw new GardenDecorException("garden decor not found with the given id:" + gardenDecorId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<GardenDecor> al = cart.get().getGardenDecor();
			al.add(gardenDecor.get());
			cart.get().setGardenDecor(al);
		}
		cost = cost + gardenDecor.get().getGardenDecorPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	// remove garden decor from cart
	@Override
	public Cart removeGardenDecorFromCart(int cartId, int gardenDecorId) 
	{
		Optional<GardenDecor> gardenDecor = gardenDecorRepo.findById(gardenDecorId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!gardenDecor.isPresent())
		{
			throw new GardenDecorException("garden decor not found with the given id:" + gardenDecorId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<GardenDecor> al = cart.get().getGardenDecor();
			al.remove(gardenDecor.get());
			cart.get().setGardenDecor(al);
		}
		cost = cost - gardenDecor.get().getGardenDecorPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	
 	// add seed to cart
	@Override
	public Cart addSeedToCart(int cartId, int seedId) 
	{
		Optional<Seed> seed = seedRepo.findById(seedId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!seed.isPresent())
		{
			throw new GardenDecorException("seed not found with the given id:" + seedId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Seed> al = cart.get().getSeed();
			al.add(seed.get());
			cart.get().setSeed(al);
		}
		cost = cost + seed.get().getPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}
	
	// remove seed from cart
	@Override
	public Cart removeSeedFromCart(int cartId, int seedId) 
	{
		Optional<Seed> seed = seedRepo.findById(seedId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!seed.isPresent())
		{
			throw new GardenDecorException("seed not found with the given id:" + seedId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Seed> al = cart.get().getSeed();
			al.remove(seed.get());
			cart.get().setSeed(al);
		}
		cost = cost - seed.get().getPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	// add plant to cart
	@Override
	public Cart addPlantToCart(int cartId, int plantId) 
	{
		Optional<Plant> plant = plantRepo.findById(plantId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!plant.isPresent())
		{
			throw new GardenDecorException("plant not found with the given id:" + plantId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Plant> al = cart.get().getPlant();
			al.add(plant.get());
			cart.get().setPlant(al);
		}
		cost = cost + plant.get().getPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	// remove plant from cart
	@Override
	public Cart removePlantFromCart(int cartId, int plantId) 
	{
		Optional<Plant> plant = plantRepo.findById(plantId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!plant.isPresent())
		{
			throw new GardenDecorException("plant not found with the given id:" + plantId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Plant> al = cart.get().getPlant();
			al.remove(plant.get());
			cart.get().setPlant(al);
		}
		cost = cost - plant.get().getPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	/**
	 * Adds the fertilizer of given id to the customer cart of given id.
	 * @param cartId
	 * @param fertilizerId
	 * @return returns the updated cart object.
	 */
	/*
	@Override
	public Cart addFertilizerToCart(int cartId, int fertilizerId) 
	{
		Optional<Fertilizer> fertilizer = fertilizerRepo.findById(fertilizerId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(fertilizer.isEmpty())
		{
			throw new FertilizerNotFoundException("fertilizer not found with the given id:" + fertilizerId);
		}
		if(cart.isEmpty())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Fertilizer> fertilizers = cart.get().getFertilizer();
			fertilizers.add(fertilizer.get());
			cart.get().setFertilizer(fertilizers);
		}
		cost = cost + fertilizer.get().getFertilizerPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	/**
	 * Removes the fertilizer of given id from the customer cart of given id.
	 * @param cartId
	 * @param fertilizerId
	 * @return returns the updated cart object.
	 */
	/*
	@Override
	public Cart removeFertilizerFromCart(int cartId, int fertilizerId) 
	{
		Optional<Fertilizer> fertilizer = fertilizerRepo.findById(fertilizerId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(fertilizer.isEmpty())
		{
			throw new FertilizerNotFoundException("fertilizer not found with the given id:" + fertilizerId);
		}
		if(cart.isEmpty())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Fertilizer> fertilizers = cart.get().getFertilizer();
			fertilizers.remove(fertilizer.get());
			cart.get().setFertilizer(fertilizers);
		}
		cost = cost - fertilizer.get().getFertilizerPrice();
		cart.get().setCartCost(cost);
		cartRepo.save(cart.get());
		return cart.get();
	}

	// add planter to cart
	@Override
	public Cart addPlanterToCart(int cartId, int planterId) 
	{
		Optional<Planter> planter = planterRepo.findById(planterId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!planter.isPresent())
		{
			throw new GardenDecorException("planter not found with the given id:" + planterId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Planter> al = cart.get().getPlanter();
			al.add(planter.get());
			cart.get().setPlanter(al);
		}
		cost = cost + planter.get().getPrice();
		cart.get().setCartCost(cost);
		return cart.get();
	}

	// remove planter from cart
	@Override
	public Cart removePlanterFromCart(int cartId, int planterId) 
	{
		Optional<Planter> planter = planterRepo.findById(planterId);
		Optional<Cart> cart = cartRepo.findById(cartId);
		double cost = cart.get().getCartCost();
		if(!planter.isPresent())
		{
			throw new GardenDecorException("planter not found with the given id:" + planterId);
		}
		if(!cart.isPresent())
		{
			throw new CartException("cart not found with the given id:" + cartId);
		}
		else
		{
			List<Planter> al = cart.get().getPlanter();
			al.remove(planter.get());
			cart.get().setPlanter(al);
		}
		cost = cost - planter.get().getPrice();
		cart.get().setCartCost(cost);
		return cart.get();
	}
	*/

}