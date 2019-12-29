package tacos.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
//end::baseClass[]
//tag::baseClass[]

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Order order, @AuthenticationPrincipal User user) {
        order.setUser(user);

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid  Order order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()){
            return "orderForm";
        }

        this.orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }

    @GetMapping("/zip")
    public String showOrderByZup( @RequestParam String zip ,Model model){
        List<Order> byDeliveryZip = this.orderRepository.findByDeliveryZip(zip);
        model.addAttribute("orders", byDeliveryZip);

        return "orderZip";
    }
}