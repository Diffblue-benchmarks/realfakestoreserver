package gq.cader.realfakestoreserver.controller;

import gq.cader.realfakestoreserver.entity.Product;
import gq.cader.realfakestoreserver.repository.ProductRepository;
import gq.cader.realfakestoreserver.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductRepository productRepository;
    private Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(value = "/findByProductId/{id}", produces = "application/json")
    public Product findByProductId(@PathVariable Integer id) {

        return productService.findById(id);
    }

    //TODO Refactor this logic into Service
    @GetMapping(value = "/findByPartialString/{query}", produces = "application/json")
    public List<Product> findByPartialString(@PathVariable String query) {
        LOG.info("Searching for " + query);
        return productRepository.findAll()
                .stream()
                .filter(x -> x.getName()
                        .toLowerCase()
                        .contains(query.toLowerCase()))
                .sorted((x, y) -> x.getName().compareToIgnoreCase(y.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public Product postNewProduct(@RequestBody Product product) {
        return productService.postNewProduct(product);
    }

}
