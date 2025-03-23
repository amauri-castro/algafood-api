package com.amauri.algafood.api.v1.openapi.model;

import com.amauri.algafood.api.v1.model.GrupoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiOperation("GruposModel")
@Data
public class GruposModelOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("GruposEmbeddedModel")
    @Data
    public class GruposEmbeddedModelOpenApi {
        private List<GrupoModel> grupos;
    }
}
