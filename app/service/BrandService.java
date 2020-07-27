package service;

import context.DatabaseExecutionContext;
import dto.Brand;
import exceptions.UsedCarServiceException;
import mappers.BrandMapper;
import models.BrandEntity;
import transformers.BrandTransformer;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.Optional.ofNullable;
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
            try {
                brandMapper.create(brand);
                return brand;
            } catch (RuntimeException e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }
}
