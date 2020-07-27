package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import context.ControllerExecutionContext;
import exceptions.UsedCarServiceException;
import play.libs.concurrent.HttpExecution;
import service.BrandService;
import models.BrandEntity;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static utils.ResponseUtils.createErrorResponse;
import static utils.ResponseUtils.createSuccessfulResponse;

public class BrandController extends Controller {
    private ControllerExecutionContext controllerExecutionContext;
    private Executor ex;
    private BrandService brandService;

    @Inject
    public BrandController(ControllerExecutionContext controllerExecutionContext,
                           Executor ex, BrandService brandService) {
        this.controllerExecutionContext = controllerExecutionContext;
        this.ex = HttpExecution.fromThread(controllerExecutionContext);
        this.brandService = brandService;
    }


    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return brandService.create(Json.fromJson(json, BrandEntity.class))
                .thenApplyAsync(brand -> created(createSuccessfulResponse(brand)),ex)
                .exceptionally(e -> internalServerError(createErrorResponse(e.getMessage())));
    }

    public Result list() {
       // var brands = brandStore.getAll();
        return ok(Json.toJson("brands"));
    }

    public CompletionStage<Result> getById(@NotNull String name) {
        return brandService.getByName(name).thenApplyAsync(optionalBrand -> optionalBrand.map(brand -> {
            JsonNode jsonObject = Json.toJson(brand);
            return ok(createSuccessfulResponse(jsonObject));
        }).orElse(notFound(createSuccessfulResponse(null))), ex);
    }
}
