package com.algaworks.algafood.core.security;

import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.amazonaws.services.s3.model.PublicAccessBlockConfiguration;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAutenticado() {
        return getAuthentication().isAuthenticated();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("usuario_id");
    }

    public boolean gerenciaRestaurante(Long restauranteId) {
        if (restauranteId == null) {
            return false;
        }
        return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
    }

    public boolean gerenciaRestauranteDoPedido(String codigoPedido) {
        return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
    }

    public boolean usuarioAutenticadoIgual(Long usuarioId) {
        return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean temEscopoEscrita() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean temEscopoLeitura() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean podeGerenciarPedidos(String codidoPedido) {
        return temEscopoEscrita() && (hasAuthority("GERENCIAR_PEDIDOS") || gerenciaRestauranteDoPedido(codidoPedido));
    }

    public boolean podeConsultarRestaurantes() {
        return temEscopoLeitura() && isAutenticado();
    }

    public boolean podeGerenciarCadastroRestaurantes() {
        return temEscopoLeitura() && hasAuthority("EDITAR_RESTAURANTES");
    }

    public boolean podeGerenciarFuncionamentoRestaurantes(Long restauranteId) {
        return temEscopoEscrita() && (hasAuthority("EDITAR_RESTAURANTES") || gerenciaRestaurante(restauranteId));
    }

    public boolean podeConsultarUsuariosGruposPermissoes() {
        return temEscopoEscrita() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean podeEditarUsuariosGruposPermissoes() {
        return temEscopoEscrita() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean podePesquisarPedidos(Long clienteId, Long restauranteId) {
        return temEscopoLeitura() && (hasAuthority("CONSULTAR_PEDIDOS") || usuarioAutenticadoIgual(clienteId)
        || usuarioAutenticadoIgual(clienteId) || gerenciaRestaurante(restauranteId));
    }

    public boolean podePesquisarPedidos() {
        return isAutenticado() && temEscopoLeitura();
    }

    public boolean podeConsultarFormasPagamento() {
        return isAutenticado() && temEscopoLeitura();
    }

    public boolean podeConsultarCidades() {
        return isAutenticado() && temEscopoLeitura();
    }

    public boolean podeConsultarEstados() {
        return isAutenticado() && temEscopoLeitura();
    }

    public boolean podeConsultarEstatisticas() {
        return temEscopoLeitura() && hasAuthority("GERAR_RELATORIOS");
    }

}
