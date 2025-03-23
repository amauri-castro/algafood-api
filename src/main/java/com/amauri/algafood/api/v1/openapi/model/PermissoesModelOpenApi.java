package com.amauri.algafood.api.v1.openapi.model;

import com.amauri.algafood.api.v1.model.PermissaoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiOperation("PermissoesModel")
@Data
public class PermissoesModelOpenApi {

    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissoesEmbeddedModelOpenApi {
        private List<PermissaoModel> permissoes;
    }
}
