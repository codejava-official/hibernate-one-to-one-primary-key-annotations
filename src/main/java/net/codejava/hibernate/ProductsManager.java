package net.codejava.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * This program demonstrates using Hibernate framework to manage a
 * bidirectional one-to-one association on a primary key using 
 * annotations.
 * @author www.codejava.net
 *
 */
public class ProductsManager {

	public static void main(String[] args) {
		// loads configuration and mappings
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
		
		// builds a session factory from the service registry
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// creates a new product
		Product product = new Product();
		product.setName("Civic");
		product.setDescription("Comfortable, fuel-saving car");
		product.setPrice(20000);
		
		// creates product detail
		ProductDetail detail = new ProductDetail();
		detail.setPartNumber("ABCDEFGHIJKL");
		detail.setDimension("2,5m x 1,4m x 1,2m");
		detail.setWeight(1000);
		detail.setManufacturer("Honda Automobile");
		detail.setOrigin("Japan");
		
		// sets the bi-directional association
		product.setProductDetail(detail);
		detail.setProduct(product);
		
		// persists the product
		session.save(product);
		
		// queries all products
		List<Product> listProducts = session.createQuery("from Product").list();
		for (Product aProd : listProducts) {
			String info = "Product: " + aProd.getName() + "\n";
			info += "\tDescription: " + aProd.getDescription() + "\n";
			info += "\tPrice: $" + aProd.getPrice() + "\n";
			
			ProductDetail aDetail = aProd.getProductDetail();
			info += "\tPart number: " + aDetail.getPartNumber() + "\n";
			info += "\tDimension: " + aDetail.getDimension() + "\n";
			info += "\tWeight: " + aDetail.getWeight() + "\n";
			info += "\tManufacturer: " + aDetail.getManufacturer() + "\n";
			info += "\tOrigin: " + aDetail.getOrigin() + "\n";
			
			System.out.println(info);
		}
		
		session.getTransaction().commit();
		session.close();
	}

}