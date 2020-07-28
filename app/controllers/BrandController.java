package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import context.ControllerExecutionContext;
import exceptions.BrandNotFoundException;
import models.BrandEntity;
import play.libs.Json;
import play.libs.concurrent.HttpExecution;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.BrandService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import static utils.ResponseUtils.createErrorResponse;
import static utils.ResponseUtils.createSuccessfulResponse;

public class BrandController extends Controller {
    private final Executor ex;
    private final BrandService brandService;

    @Inject
    public BrandController(ControllerExecutionContext controllerExecutionContext,
                           Executor ex, BrandService brandService) {
        this.ex = HttpExecution.fromThread(controllerExecutionContext);
        this.brandService = brandService;
    }


    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return brandService.create(Json.fromJson(json, BrandEntity.class))
                .thenApplyAsync(brand -> created(createSuccessfulResponse(brand)),ex)
                .exceptionally(e -> internalServerError(createErrorResponse(e.getMessage())));
    }

    public CompletionStage<Result> update(Http.Request request) {
        JsonNode json = request.body().asJson();
        return brandService.update(Json.fromJson(json, BrandEntity.class))
                .thenApplyAsync(brand -> created(createSuccessfulResponse(brand)),ex)
                .exceptionally(e -> e.getCause() instanceof BrandNotFoundException ?
                        notFound(createErrorResponse(e.getMessage())) : internalServerError(createErrorResponse(e.getMessage())));
    }

    public CompletionStage<Result> getById(@NotNull String name) {
        return brandService.getByName(name).thenApplyAsync(optionalBrand -> optionalBrand.map(brand -> {
            JsonNode jsonObject = Json.toJson(brand);
            return ok(createSuccessfulResponse(jsonObject));
        }).orElse(notFound(createSuccessfulResponse(null))), ex);
    }

    public CompletionStage<Result> delete(@NotNull String name) {
        return brandService.delete(name)
                .thenApplyAsync(v -> ok(createSuccessfulResponse(null)), ex)
                .exceptionally(e -> e.getCause() instanceof BrandNotFoundException?
                        notFound(createErrorResponse(e.getMessage())) : internalServerError(createErrorResponse(e.getMessage())));
    }
}
