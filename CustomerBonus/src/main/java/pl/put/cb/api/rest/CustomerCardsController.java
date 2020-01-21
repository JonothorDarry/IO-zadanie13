package pl.put.cb.api.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.cb.dao.CustomersDAO;
import pl.put.cb.model.Customer;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/customer_cards")
public class CustomerCardsController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerCardsController.class);
    
    private List<Integer> customerIds;
    private List<Customer> s;
    private Double v;
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Integer> get(@RequestParam(value="threshold", defaultValue="1.0") Double threshold) {
        customerIds = new ArrayList<Integer>();
        
        //Zamiana w pętli CustomersDAO.getInstance().getCustomers() na zainicjalizowaną zmienną
        s=CustomersDAO.getInstance().getCustomers();
        
        //Nie wyznaczam averageSpendings w pętli - zamiast tego wyznaczam je przed pętlą
        //Zaliczyłem zmianę: z average (time)=5810 i throughput=1.5/sec do average (time)=49 i throughput=20.9/sec
        v=averageSpendings();
        for (Customer customer : s){
            if (v > 0.0 &&
                    customer.getSpendings() / v >= threshold){
                customerIds.add(customer.getId());
                logger.debug(customer.getId()+"");
            }
        }
        return customerIds;
    }

    private Double averageSpendings() {
        Double result = 0.0;
        s=CustomersDAO.getInstance().getCustomers();
        
        if (s.isEmpty()) return result;
        for (Customer customer : s){
            result += customer.getSpendings();
        }

        return result / s.size();
    }


}


