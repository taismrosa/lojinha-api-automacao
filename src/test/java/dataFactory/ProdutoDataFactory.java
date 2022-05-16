package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();
        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();

        produto.setProdutoNome("Nome do produto");
        produto.setProdutoValor(valor);
        List<String> cores = new ArrayList<>();
        cores.add("Roxo");
        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        componente.setComponenteNome("Nome do componente");
        componente.setComponenteQuantidade(4);
        componentes.add(componente);
        produto.setComponentes(componentes);

        return produto;
    }
}
