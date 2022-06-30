package com.algaworks.algafood.api.controller.openapi.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PagedModelOpenApi<T> {

    private List<T> content;

    @ApiModelProperty(example = "10", value = "Quantidade de registros por página")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total de registros")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total de páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Número de página (Começa em 0)")
    private Long number;
}
