package personal.learning.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import personal.learning.configuration.DBConfiguration;
import personal.learning.model.entity.Brand;
import personal.learning.model.entity.Product;

@Repository
public class ProductDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
    }

	public List<Product> getProductsByBrandName(String brandName) {
		Session session = getSession();
		Transaction txn = null;
		List<Product> productList = new ArrayList<Product>();
		try {
			txn = session.beginTransaction();
			StringBuffer sb = new StringBuffer();
			sb.append("from PRODUCT where BRAND_ID in");
			sb.append("( select brandId from BRAND where brandName = '"+brandName+"' )");
			String queryString = sb.toString();
			Query query = session.createQuery(queryString);
			//query.setInteger("brandId", brandId);
			//query.setParameter("brandName", brandName);
			productList = (List<Product>)query.getResultList();
			txn.commit();
		} catch(Exception ex) {
			if(txn != null) {
				txn.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
			//sessionFactory.close();
		}
		return productList;
	}

}
