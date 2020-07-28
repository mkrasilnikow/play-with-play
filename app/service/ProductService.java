package service;

import context.DatabaseExecutionContext;
import exceptions.ModelNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.UsedCarServiceException;
import io.jsonwebtoken.lang.Assert;
import mappers.ModelMapper;
import mappers.ProductMapper;
import models.ModelEntity;
import models.ProductItemEntity;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

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
}
