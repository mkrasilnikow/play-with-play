package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import context.ControllerExecutionContext;
import exceptions.ModelNotFoundException;
import models.ModelEntity;
import play.libs.Json;
import play.libs.concurrent.HttpExecution;
import play.mvc.Http;
import play.mvc.Result;
import service.ModelService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import static play.mvc.Results.*;
import static utils.ResponseUtils.createErrorResponse;
import static utils.ResponseUtils.createSuccessfulResponse;

public class ModelController {
    private final Executor ex;
    private final ModelService modelService;

    @Inject
    public ModelController(ControllerExecutionContext controllerExecutionContext, ModelService modelService) {
        this.ex = HttpExecution.fromThread(controllerExecutionContext);
        this.modelService = modelService;
    }


    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return modelService.create(Json.fromJson(json, ModelEntity.class))
                .thenApplyAsync(model -> created(createSuccessfulResponse(model)),ex)
                .exceptionally(e -> internalServerError(createErrorResponse(e.getMessage())));
    }

    public CompletionStage<Result> update(Http.Request request) {
        JsonNode json = request.body().asJson();
        return modelService.update(Json.fromJson(json, ModelEntity.class))
                .thenApplyAsync(model -> created(createSuccessfulResponse(model)),ex)
                .exceptionally(e -> e.getCause() instanceof ModelNotFoundException ?
                        notFound(createErrorResponse(e.getMessage())) : internalServerError(createErrorResponse(e.getMessage())));
    }

    public CompletionStage<Result> getByName(@NotNull String name) {
        return modelService.getByName(name).thenApplyAsync(optionalModelEntity -> optionalModelEntity.map(model -> {
            JsonNode jsonObject = Json.toJson(model);
            return ok(createSuccessfulResponse(jsonObject));
        }).orElse(notFound(createSuccessfulResponse(null))), ex);
    }

    public CompletionStage<Result> delete(@NotNull String name) {
        return modelService.delete(name)
                .thenApplyAsync(v -> ok(createSuccessfulResponse(null)), ex)
                .exceptionally(e -> e.getCause() instanceof ModelNotFoundException?
                        notFound(createErrorResponse(e.getMessage())) : internalServerError(createErrorResponse(e.getMessage())));
    }
}
