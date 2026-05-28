package com.fatec.product.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import com.fatec.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import com.fatec.product.dtos.ProductRequest;
import com.fatec.product.dtos.ProductResponse;
import com.fatec.product.entities.Product;
import com.fatec.product.mappers.ProductMapper;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repoP;

    public List<ProductResponse> findAll() {
        return repoP.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    public ProductResponse findById(Long id) {
        return repoP.findById(id).map(ProductMapper::toDTO).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public void deleteById(Long id) {
        if (repoP.existsById(id)) {
            repoP.deleteById(id);
        } else {
            throw new EntityNotFoundException("Produto não encontrado");
        }
    }

    public ProductResponse save(ProductRequest product) {
        Product p = repoP.save(ProductMapper.toEntity(product));
        return ProductMapper.toDTO(p);
    }

    public void update(Long id, ProductRequest product) {
        Product p = repoP.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        p.setDescription(product.description());
        p.setName(product.name());
        p.setPrice(product.price());

        repoP.save(p); // update
    }
}
