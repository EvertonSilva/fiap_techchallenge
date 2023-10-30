package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.ItemPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Quantidade;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoAdapter implements IAdapter<ItemPedido, ItemPedidoModel> {

    private ProdutoAdapter produtoAdapter;

    @Autowired
    public ItemPedidoAdapter(ProdutoAdapter produtoAdapter) {
        this.produtoAdapter = produtoAdapter;
    }

    @Override
    public ItemPedido toDomain(ItemPedidoModel itemPedidoModel) {
        var itemPedido = new ItemPedido();

        itemPedido.setProduto(produtoAdapter.toDomain(itemPedidoModel.getProduto()));
        itemPedido.setQuantidade(new Quantidade(itemPedidoModel.getQuantidade()));

        return itemPedido;
    }

    @Override
    public ItemPedidoModel toModel(ItemPedido itemPedido) {
        var itemPedidoModel = new ItemPedidoModel();

        itemPedidoModel.setQuantidade(itemPedido.getQuantidade().getValor());
        itemPedidoModel.setProduto(produtoAdapter.toModel(itemPedido.getProduto()));

        return itemPedidoModel;
    }
}
