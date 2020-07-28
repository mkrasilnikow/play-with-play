package service;

import context.DatabaseExecutionContext;
import exceptions.ModelNotFoundException;
import exceptions.UsedCarServiceException;
import io.jsonwebtoken.lang.Assert;
import mappers.ModelMapper;
import models.ModelEntity;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.Optional.ofNullable;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ModelService {
    private final ModelMapper modelMapper;
    private final DatabaseExecutionContext mapperExecutionContext;

    @Inject
    public ModelService(ModelMapper modelMapper, DatabaseExecutionContext mapperExecutionContext) {
        this.modelMapper = modelMapper;
        this.mapperExecutionContext = mapperExecutionContext;
    }

    public CompletionStage<Optional<ModelEntity>> getByName(String name) {
        return supplyAsync(() -> ofNullable(modelMapper.getByName(name)),
                mapperExecutionContext);
    }

    public CompletionStage<ModelEntity> create(ModelEntity model) {
        return supplyAsync(() -> {
            Assert.notNull(model, "Model entity must not be null!");
            try {
                modelMapper.create(model);
                return model;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    public CompletionStage<ModelEntity> update(ModelEntity model) {
        return supplyAsync(() -> {
            Assert.notNull(model, "Model entity must not be null!");
            ofNullable(modelMapper.getByName(model.getName()))
                    .orElseThrow(() -> new ModelNotFoundException("Not found: " + model.getName()));
            try {
                modelMapper.update(model);
                return model;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }

    public CompletionStage<Boolean> delete(String name) {
        return supplyAsync(() -> {
            ofNullable(modelMapper.getByName(name))
                    .orElseThrow(() -> new ModelNotFoundException("Not found: " + name));
            try {
                modelMapper.delete(name);
                return true;
            } catch (Exception e) {
                throw new UsedCarServiceException(e.getMessage());
            }
        }, mapperExecutionContext);
    }
}
