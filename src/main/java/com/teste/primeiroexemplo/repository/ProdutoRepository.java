package com.teste.primeiroexemplo.repository;
/* 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.primeiroexemplo.model.Produto;
/* 
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {
    /* 
    //simulando banco de dados
    private List<Produto> produtos = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos.
     * @return Lista de produtos.
     */

     /* 
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     * @param id do produto que sera localizado.
     * @return Retorna o produto caso seja encontrado.
     */

     /* 
    public Optional<Produto> obterPorId(Integer id){
        return produtos.stream().filter(p -> p.getId() == id)
        .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado na lista.
     * @return Retorna o produto que foi adicionado na lista.
     */
    /* 
    public Produto adicionar(Produto produto){
         ultimoId++;
         produto.setId(ultimoId);
         produtos.add(produto);
         return produto;
    }

    /**
     * Metodo para deletar um produto por id.
     * @param id do produto a ser deletado.
     */

     /* 
    public void deletar(Integer id){
        produtos.removeIf(p -> p.getId() == id);
    }

    /**
     * Metodo para atualizar o produto na lista.
     * @param produto que sera atualizado.
     * @return retorna o produto após atualizar a lista.
     */
    /* 
    public Produto atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
         
        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("produto não encontrado");
        }

        deletar(produto.getId());
        produtos.add(produto);

        return produto;
    }  
      */
}
