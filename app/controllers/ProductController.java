package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import context.ControllerExecutionContext;
import exceptions.ProductNotFoundException;
import models.ProductItemEntity;
import play.libs.Json;
import play.libs.concurrent.HttpExecution;
import play.mvc.Http;
import play.mvc.Result;
import service.ProductService;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import static play.mvc.Results.*;
import static utils.ResponseUtils.createErrorResponse;
import static utils.ResponseUtils.createSuccessfulResponse;

public class ProductController {
    private final Executor ex;
    private final ProductService productService;

    @Inject
    public ProductController(ControllerExecutionContext controllerExecutionContext, ProductService productService) {
        this.ex = HttpExecution.fromThread(controllerExecutionContext);
        this.productService = productService;
    }

    public CompletionStage<Result> searchByBrandAndOptionalModel(String brand, String model) {
        return productService.searchByBrandAndOptionalModel(brand, model)
                .thenApplyAsync(product -> {
                    JsonNode jsonObject = Json.toJson(product);
                    return ok(createSuccessfulResponse(jsonObject));
                }, ex);
    }

    public CompletionStage<Result> fuzzySearchByModel(String model) {
        return productService.fuzzySearchByModel(model)
                .thenApplyAsync(product -> {
                    JsonNode jsonObject = Json.toJson(product);
                    return ok(createSuccessfulResponse(jsonObject));
                }, ex);
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return productService.create(Json.fromJson(json, ProductItemEntity.class))
                .thenApplyAsync(product -> created(createSuccessfulResponse(product)),ex)
                .exceptionally(e -> internalServerError(createErrorResponse(e.getMessage())));
    }

    public CompletionStage<Result> update(Http.Request request) {
        JsonNode json = request.body().asJson();
        return productService.update(Json.fromJson(json, ProductItemEntity.class))
                .thenApplyAsync(product -> created(createSuccessfulResponse(product)),ex)
                .exceptionally(e -> e.getCause() instanceof ProductNotFoundException ?
                        notFound(createErrorResponse(e.getMessage())) : internalServerError(createErrorResponse(e.getMessage())));
    }

    public CompletionStage<Result> getById(Integer id) {
        return productService.getById(id).thenApplyAsync(optionalProduct -> optionalProduct.map(product -> {
            JsonNode jsonObject = Json.toJson(product);
            return ok(createSuccessfulResponse(jsonObject));
        }).orElse(notFound(createSuccessfulResponse(null))), ex);
    }

    public CompletionStage<Result> delete(Integer id) {
        return productService.delete(id)
                .thenApplyAsync(v -> ok(createSuccessfulResponse(null)), ex)
                .exceptionally(e -> e.getCause() instanceof ProductNotFoundException?
                        notFound(createErrorResponse(e.getMessage())) : internalServerError(createErrorResponse(e.getMessage())));
    }
}
