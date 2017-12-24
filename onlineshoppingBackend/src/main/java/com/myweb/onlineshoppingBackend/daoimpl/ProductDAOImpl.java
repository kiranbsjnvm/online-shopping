package com.myweb.onlineshoppingBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.onlineshoppingBackend.dao.ProductDAO;
import com.myweb.onlineshoppingBackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Product get(int productId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(Product.class,Integer.valueOf(productId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}

	@Override
	public List<Product> list() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Product" , Product.class).getResultList();
	}

	@Override
	public boolean add(Product product) {
		try {		
			
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();	
			return false;
		}		
		
	}

	@Override
	public boolean update(Product product) {
		
		try {			
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;		
	}

	
	@Override
	public boolean delete(Product product) {
		try {
			
			product.setActive(false);
			// call the update method
			return this.update(product);
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;			
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProducts);
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategoryId = "FROM Product WHERE active = :active AND categoryId=:category_Id";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategoryId,Product.class);
		query.setParameter("active", true);
		query.setParameter("category_Id", categoryId);
		
		return query.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProducts = "FROM Product WHERE active = :active ORDER BY id";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProducts,Product.class);
		query.setParameter("active", true);
		query.setFirstResult(0);
		query.setMaxResults(count); 
		
		return query.getResultList();
	}

}
