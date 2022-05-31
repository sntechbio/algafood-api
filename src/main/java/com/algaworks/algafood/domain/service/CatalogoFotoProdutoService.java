package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.exceptionhandler.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FotoStorageService fotoStorageService;

    public CatalogoFotoProdutoService(ProdutoRepository produtoRepository, FotoStorageService fotoStorageService) {
        this.produtoRepository = produtoRepository;
        this.fotoStorageService = fotoStorageService;
    }

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
//                .contentType(foto.getContentType())
                .inputStream(dadosArquivo).build();

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(() ->
                new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
    }

    @Transactional
    public void excluir(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);
        produtoRepository.delete(foto);
        produtoRepository.flush();
        fotoStorageService.remover(foto.getNomeArquivo());
    }

}
