package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import mapper.BrandStore;
import model.BrandEntity;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.twirl.api.Html;
import utils.ResponseUtils;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.*;

public class BrandController extends Controller {
    private HttpExecutionContext executionContext;
    private BrandStore brandStore;

    @Inject
    public BrandController(HttpExecutionContext executionContext, BrandStore brandStore) {
        this.executionContext = executionContext;
        this.brandStore = brandStore;
    }


    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return supplyAsync(() -> {
            if (json == null) {
                return badRequest(ResponseUtils.createResponse("Bad request", false));
            }
            Optional<BrandEntity> brandEntityOptional = brandStore.addBrand(Json.fromJson(json, BrandEntity.class));
            return brandEntityOptional.map(brand -> {
                JsonNode jsonObject = Json.toJson(brand);
                return created(ResponseUtils.createResponse(jsonObject, true));
            }).orElse(internalServerError(ResponseUtils.createResponse("Could not create data.", false)));
        }, executionContext.current());
    }

    public Result list() {
        var brands = brandStore.getAll();
        return ok(Json.toJson(brands));
    }
}
