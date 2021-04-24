package br.brunocatao.estoque;

import java.util.ArrayList;

public class Estoque {
	private ArrayList<Produto> produtos;
	
	public Estoque() {
		produtos = new ArrayList<>();
	}
	
	public void adicionarProduto(Produto p) {
		produtos.add(p);
	}
	
	public Produto getProdutoMaisBarato() {
		Produto maisBarato = null;
		
		for (Produto p : produtos) {
			if (maisBarato == null) {
				maisBarato = p;
			} else {
				if (p.getValor() < maisBarato.getValor()) {
					maisBarato = p;
				}
			}
		}
		
		return maisBarato;
	}

	public Produto getProdutoMaisCaro() {
		Produto maisCaro = null;
		
		for (Produto p : produtos) {
			if (maisCaro == null) {
				maisCaro = p;
			} else {
				if (p.getValor() > maisCaro.getValor()) {
					maisCaro = p;
				}
			}
		}
		
		return maisCaro;
	}
	
	public void comprar(int codigo, int quantidade) throws Exception {
		for (Produto p : produtos) {
			if (p.getCodigo() == codigo) { // ACHEI O PRODUTO QUE EU ESTOU PROCURANDO
				// ATENÇÃO - não pode deixar comprar mais produtos do que tem
				if (quantidade > p.getQuantidade()) {
					throw new Exception("Quantidade insuficiente em estoque");
				} else { // pode comprar, porque tem no estoque
					// decrementando a quantidade do produto no estoque
					p.setQuantidade(p.getQuantidade() - quantidade);
				}
			}
		}
	}

	public void repor(int codigo, int quantidade)  {
		for (Produto p : produtos) {
			if (p.getCodigo() == codigo) { // ACHEI O PRODUTO QUE EU ESTOU PROCURANDO
				// incrementando a quantidade do produto no estoque
				p.setQuantidade(p.getQuantidade() + quantidade);
			}
		}
	}
	
	public ArrayList<Produto> getProdutosQuePrecisamRepor() {
		ArrayList<Produto> precisamRepor = new ArrayList<>();
		
		for (Produto p : produtos) {
			if (p.getQuantidade() == 0) {
				precisamRepor.add(p);
			}
		}
		
		return precisamRepor;
	}
	
	public void exibirEstoque() {
		for (Produto p : produtos) {
			System.out.println(p.getCodigo() + " - " + p.getNome() + " - R$ " + p.getValor() + " - Qtd: " + p.getQuantidade());
		}
	}
	
	public static void main(String[] args) {
		// Crio um objeto da classe Estoque
		Estoque estoque = new Estoque();
		// Adicionar produtos nesse estoque
		estoque.adicionarProduto(new Produto(123, "Mouse", 65.50, 20));
		estoque.adicionarProduto(new Produto(124, "Teclado", 80, 5));
		estoque.adicionarProduto(new Produto(125, "Monitor", 300, 2));
		estoque.adicionarProduto(new Produto(126, "Fonte", 120, 30));
		estoque.adicionarProduto(new Produto(127, "Pendrive", 18, 7));
		estoque.adicionarProduto(new Produto(128, "SSD", 500, 4));
		
		System.out.println("PRODUTOS DO ESTOQUE:");
		estoque.exibirEstoque();

		System.out.println("O produto mais barato do meu estoque é: ");
		Produto barato = estoque.getProdutoMaisBarato();
		System.out.println(barato.getNome() + " - R$ " + barato.getValor());
		
		System.out.println("O produto mais caro do meu estoque é: ");
		Produto caro = estoque.getProdutoMaisCaro();
		System.out.println(caro.getNome() + " - R$ " + caro.getValor());
		
		// Vou simular uma compra de teclados
		System.out.println("Vou comprar 3 teclados");
		try {
			estoque.comprar(124, 5);
			estoque.comprar(125, 2);
			estoque.comprar(128, 4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("PRODUTOS DO ESTOQUE:");
		estoque.exibirEstoque();
		
		System.out.println("Produtos que precisam ser repostos");
		ArrayList<Produto> listaDeCompras = estoque.getProdutosQuePrecisamRepor();
		for (Produto p : listaDeCompras) {
			System.out.println(p.getCodigo() + " - " + p.getNome());
		}
	}
}
