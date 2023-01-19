package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.ProdutoRepository;
import com.teste.primeiroexemplo.shared.ProdutoDTO;


@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

     /**
     * Metodo para retornar uma lista de produtos.
     * @return Lista de produtos.
     */
    public List<ProdutoDTO> obterTodos(){
        //Retorna uma lista de produtos model
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     * @param id do produto que sera localizado.
     * @return Retorna o produto caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        //obtendo optional de produto por id
        Optional<Produto> produto = produtoRepository.findById(id);

        //se nao encontrar, lança uma exceção
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id  + " Não encontrado.");
        }
        //convertendo o optional de produto em um protudoDTO
        ProdutoDTO produtoDto =  new ModelMapper().map(produto.get(), ProdutoDTO.class);
        //criando e retornando um optional de produtoDTO
        return Optional.of(produtoDto);
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado na lista.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        //Removendo o id para poder adicionar
        produtoDto.setId(null);

        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter um produto DTO em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        produto.setId(null);
        //Salvar o produto do banco
        produto = produtoRepository.save(produto);

        //Pega o id que veio do banco no produto e coloca no produtoDTO
        produtoDto.setId(produto.getId());

        //Retornar o produto atualizado sem converter de novo. usando o produtoDTO
        return produtoDto;
   }

   /**
     * Metodo para deletar um produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        //verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);

        //se nao existir lança uma exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id  + " Não encontrado. Produto não deletado!");
        }
        
        //Deleta o produto pelo id.
        produtoRepository.deleteById(id);
    }

    /* 
    /**
     * Metodo para atualizar o produto na lista.
     * @param produto que sera atualizado.
     * @param id do produto.
     * @return retorna o produto após atualizar a lista.
     */
    /* 
    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }  */

    /*
     * Metodo para atualizar o produto na lista.
     * @param produto que sera atualizado.
     * @param id do produto.
     * @return retorna o produto após atualizar a lista.
     */
     
    /* 
    public Produto atualizar(Produto produto, Integer id){
        Optional<Produto> produtoEncontrado = obterPorId(id);
         
        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("produto não encontrado");
        }
        produto.setId(id);
        deletar(id);
        adicionar(produto);

        return produto;
    }  */

    /**
     * Metodo para atualizar o produto na lista.
     * @param produto que sera atualizado.
     * @param id do produto.
     * @return retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar (Integer id, ProdutoDTO produtoDTO) {
        //Passar o id para o produtoDTO
        produtoDTO.setId(id);

        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter o produtoDTO em um produto
        Produto produto = mapper.map(produtoDTO, Produto.class);

        //Atualizar o produto no banco de dados
        produtoRepository.save(produto);

        //Retornar o produto DTO atualizado
        return produtoDTO;
	}
}
