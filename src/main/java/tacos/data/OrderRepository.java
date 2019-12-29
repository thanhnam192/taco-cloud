package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
     List<Order> findByDeliveryZip(String zip);

}
