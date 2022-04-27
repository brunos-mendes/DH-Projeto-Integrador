package br.com.meli.dhprojetointegrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.dhprojetointegrador.entity.PurchaseOrder;
import br.com.meli.dhprojetointegrador.service.CartProductService;
import br.com.meli.dhprojetointegrador.service.OrderService;

@RestController
@RequestMapping(OrderController.baseUri)
public class OrderController {
    public static final String baseUri = "/api/v1/fresh-products/orders/";
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartProductService cartProductService;

    @PutMapping("{idorder}")
    public ResponseEntity<PurchaseOrder> ModifyExistingOrder(@PathVariable Long idorder) {
        PurchaseOrder newOrderStatus = orderService.atualizar(idorder);
        return ResponseEntity.ok(newOrderStatus);
    }

    @GetMapping
    public ResponseEntity<?> ShowProductsOrder(@RequestParam Long idOrder) {
        return new ResponseEntity<>(cartProductService.getProductsByOrderId(idOrder), HttpStatus.OK);
    }
}