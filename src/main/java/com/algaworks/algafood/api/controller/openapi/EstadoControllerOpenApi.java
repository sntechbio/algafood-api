package com.algaworks.algafood.api.controller.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Listar os estados")
    List<EstadoModel> listar();

    @ApiOperation("Buscar um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoModel buscar(
            @ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId
    );

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses(
            @ApiResponse(code = 201, message = "Estado cadastrado")
    )
    EstadoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo estado", required = true) EstadoInput estadoInput
            );

    EstadoModel atualizar(
            @ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId,

            @ApiParam(name = "Corpo", value = "Representação de um estado com os novos dados", required = true)
            EstadoInput estadoInput
    );

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluido"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void remover(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long estadoId
    );


}
