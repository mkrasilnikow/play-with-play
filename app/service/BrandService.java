package service;

import context.DatabaseExecutionContext;
import dto.Brand;
import exceptions.BrandNotFoundException;
import exceptions.UsedCarServiceException;
import io.jsonwebtoken.lang.Assert;
import mappers.BrandMapper;
import models.BrandEntity;
import transformers.BrandTransformer;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.Optional.ofNullable;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class BrandService {
    private BrandMapper brandMapper;
    private DatabaseExecutionContext mapperExecutionContext;

    @Inject
    public BrandService(BrandMapper brandMapper, DatabaseExecutionContext mapperExecutionContext) {
        this.brandMapper = brandMapper;
        this.mapperExecutionContext = mapperExecutionContext;
    }

    public CompletionStage<Optional<Brand>> getByName(String name) {
        return supplyAsync(() -> ofNullable(BrandTransformer.INSTANCE.map(brandMapper.getByName(name))),
                mapperExecutionContext);
    }

    public CompletionStage<BrandEntity> create(BrandEntity brand) {
        return supplyAsync(() -> {
            Assert.notNull(brand, "Brand entity must not be null!");
            try {
                brandMapper.create(brand);
                return brand;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    public CompletionStage<BrandEntity> update(BrandEntity brand) {
        return supplyAsync(() -> {
            Assert.notNull(brand, "Brand entity must not be null!");
            ofNullable(brandMapper.getByName(brand.getName()))
                    .orElseThrow(() -> new BrandNotFoundException("Not found: " + brand.getName()));
            try {
                brandMapper.update(brand);
                return brand;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    public CompletionStage<Boolean> delete(String name) {
        return supplyAsync(() -> {
            ofNullable(brandMapper.getByName(name))
                    .orElseThrow(() -> new BrandNotFoundException("Not found: " + name));
            try {
                brandMapper.delete(name);
                return true;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }
}
