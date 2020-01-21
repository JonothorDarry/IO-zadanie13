package pl.put.cb.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.cb.model.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a naive implementation of a "database".
 */
public class CustomersDAO {

    private static final CustomersDAO instance = new CustomersDAO();

    private Logger logger;

    private List<Customer> customers = new ArrayList<Customer>();

    private CustomersDAO(){

        logger = LoggerFactory.getLogger(CustomersDAO.class);

        for(int i=0; i < 10000; i++){
            customers.add(new Customer(i, Math.random()*10000));
        }
        logger.debug("Database created");

    }

    public static CustomersDAO getInstance(){
        return instance;
    }

    public List<Customer> getCustomers(){
        return Collections.unmodifiableList(customers);
    }

    public Customer getCustomer(int id) {
        for (Customer customer : customers){
            if (id == customer.getId()){
                return customer;
            }
        }
        return null;
    }
}
