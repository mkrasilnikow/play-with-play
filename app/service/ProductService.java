package service;

import akka.http.impl.engine.ws.FrameHandler;
import context.DatabaseExecutionContext;
import exceptions.ProductNotFoundException;
import exceptions.UsedCarServiceException;
import io.jsonwebtoken.lang.Assert;
import mappers.ProductMapper;
import models.ProductItemEntity;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductService {
    private final ProductMapper productMapper;
    private final DatabaseExecutionContext mapperExecutionContext;

    @Inject
    public ProductService(ProductMapper productMapper, DatabaseExecutionContext mapperExecutionContext) {
        this.productMapper = productMapper;
        this.mapperExecutionContext = mapperExecutionContext;
    }

    public CompletionStage<Optional<ProductItemEntity>> getById(int id) {
        return supplyAsync(() -> ofNullable(productMapper.getById(id)),
                mapperExecutionContext);
    }

    public CompletionStage<List<ProductItemEntity>> searchByBrandAndOptionalModel(String brand, String model) {
        System.out.println(">> " + brand + " " + model);
        return supplyAsync(() -> {
            if (model != null) {
                System.out.println(">> present");
                return productMapper.searchByBrandAndModel(brand, model);
            } else {
                System.out.println(">> NOT present");
                return productMapper.searchByBrand(brand);
            }
        }, mapperExecutionContext);
/*        return supplyAsync(() -> ofNullable(
                model.map(v -> productMapper.searchByBrandAndModel(brand, v))
                     .orElseGet(() -> productMapper.searchByBrand(brand))
        ).orElse(emptyList()), mapperExecutionContext);*/
    }

    public CompletionStage<List<ProductItemEntity>> fuzzySearchByModel(String model) {
        return supplyAsync(() -> ofNullable(productMapper.fuzzySearchByModel(wrap(model))).orElse(emptyList()),
                mapperExecutionContext);
    }

    public CompletionStage<ProductItemEntity> create(ProductItemEntity entity) {
        return supplyAsync(() -> {
            Assert.notNull(entity, "Product entity must not be null!");
            try {
                var id = productMapper.create(entity);
                entity.setId(id);
                return entity;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    public CompletionStage<ProductItemEntity> update(ProductItemEntity entity) {
        return supplyAsync(() -> {
            Assert.notNull(entity, "Product entity must not be null!");
            ofNullable(productMapper.getById(entity.getId()))
                    .orElseThrow(() -> new ProductNotFoundException("Not found: " + entity.getId()));
            try {
                productMapper.update(entity);
                return entity;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    public CompletionStage<Boolean> delete(int id) {
        return supplyAsync(() -> {
            ofNullable(productMapper.getById(id))
                    .orElseThrow(() -> new ProductNotFoundException("Not found: " + id));
            try {
                productMapper.delete(id);
                return true;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    private static String wrap(String value) {
        return "%" + value + "%";
    }
}
