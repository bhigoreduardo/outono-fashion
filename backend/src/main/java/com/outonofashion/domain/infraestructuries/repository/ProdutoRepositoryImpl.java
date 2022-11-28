package com.outonofashion.domain.infraestructuries.repository;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.model.Cor;
import com.outonofashion.domain.model.Estoque;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tamanho;
import com.outonofashion.domain.model.Tipo;
import com.outonofashion.domain.repository.CategoriaRepository;
import com.outonofashion.domain.repository.CorRepository;
import com.outonofashion.domain.repository.GeneroRepository;
import com.outonofashion.domain.repository.MarcaRepository;
import com.outonofashion.domain.repository.ProdutoRepositoryQueries;
import com.outonofashion.domain.repository.TamanhoRepository;
import com.outonofashion.domain.repository.TipoRepository;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private TipoRepository tipoRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private TamanhoRepository tamanhoRepository;

	@Autowired
	private CorRepository corRepository;

	@Override
	public List<Produto> findProdutos(String[] categoria, String[] tipo, String[] genero, String[] tamanho,
			String[] marca, String[] cor, String precoMin, String precoMax, String order) {

		var jpql = new StringBuilder();
		var parameters = new HashMap<String, Object>();

		jpql.append("SELECT p FROM Produto p WHERE 0 = 0 ");

		if (categoria.length != 0) {
			jpql.append("AND p.categoria IN :categorias ");
			List<Categoria> categorias = new ArrayList<>();

			for (int i = 0; i < categoria.length; i++) {
				categorias.add(categoriaRepository.findByDescricao(categoria[i]).get());
			}
			parameters.put("categorias", categorias);
		}

		if (tipo.length != 0) {
			jpql.append("AND p.tipo IN :tipos ");
			List<Tipo> tipos = new ArrayList<>();
			
			for (int i = 0; i < tipo.length; i++) {
				tipos.add(tipoRepository.findByDescricao(tipo[i]).get());
			}
			parameters.put("tipos", tipos);
		}
		
		if (genero.length != 0) {
			jpql.append("AND p.genero IN :generos ");
			List<Genero> generos = new ArrayList<>();
			
			for (int i = 0; i < genero.length; i++) {
				generos.add(generoRepository.findByDescricao(genero[i]).get());
			}
			parameters.put("generos", generos);
		}
		
		if (marca.length != 0) {
			jpql.append("AND p.marca IN :marcas ");
			List<Marca> marcas = new ArrayList<>();
			
			for (int i = 0; i < marca.length; i++) {
				marcas.add(marcaRepository.findByDescricao(marca[i]).get());
			}
			parameters.put("marcas", marcas);
		}
		
		if (tamanho.length != 0) {
			var jpqlEstoque = new StringBuilder();
			var parametersEstoque = new HashMap<String, Object>();

			jpqlEstoque.append("SELECT e FROM Estoque e WHERE 0 = 0 AND e.tamanho IN :tamanhos");
			List<Tamanho> tamanhos = new ArrayList<>();
			
			for (int i = 0; i < tamanho.length; i++) {
				tamanhos.add(tamanhoRepository.findByDescricao(tamanho[i]).get());
			}
			parametersEstoque.put("tamanhos", tamanhos);
			
			TypedQuery<Estoque> queryEstoque = entityManager.createQuery(jpqlEstoque.toString(), Estoque.class);
			parametersEstoque.forEach((key, value) -> queryEstoque.setParameter(key, value));
			
			List<Long> produtosTamanhoId = new ArrayList<>();
			queryEstoque.getResultList().forEach(estoque -> {
				produtosTamanhoId.add(estoque.getProduto().getId());
			});
			
			jpql.append("AND p.id IN :produtosTamanhoId ");
			parameters.put("produtosTamanhoId", produtosTamanhoId);
		}
		
		if (cor.length != 0) {
			var jpqlEstoque = new StringBuilder();
			var parametersEstoque = new HashMap<String, Object>();
			
			jpqlEstoque.append("SELECT e FROM Estoque e WHERE 0 = 0 AND e.cor IN :cores");
			List<Cor> cores = new ArrayList<>();
			
			for (int i = 0; i < cor.length; i++) {
				cores.add(corRepository.findByDescricao(cor[i]).get());
			}
			parametersEstoque.put("cores", cores);
			
			TypedQuery<Estoque> queryEstoque = entityManager.createQuery(jpqlEstoque.toString(), Estoque.class);
			parametersEstoque.forEach((key, value) -> queryEstoque.setParameter(key, value));
			
			List<Long> produtosCorId = new ArrayList<>();
			queryEstoque.getResultList().forEach(estoque -> {
				produtosCorId.add(estoque.getProduto().getId());
			});
			
			jpql.append("AND p.id IN :produtosCorId ");
			parameters.put("produtosCorId", produtosCorId);
		}
		
		if (precoMin.length() != 0 && precoMax.length() != 0) {
			var jpqlEstoque = new StringBuilder();
			var parametersEstoque = new HashMap<String, Object>();
			
			jpqlEstoque.append("SELECT e FROM Estoque e WHERE 0 = 0 AND e.preco BETWEEN :precoMin AND :precoMax");
			parametersEstoque.put("precoMin", new BigDecimal(precoMin));
			parametersEstoque.put("precoMax", new BigDecimal(precoMax));
			
			TypedQuery<Estoque> queryEstoque = entityManager.createQuery(jpqlEstoque.toString(), Estoque.class);
			parametersEstoque.forEach((key, value) -> queryEstoque.setParameter(key, value));
			
			List<Long> produtosPrecoId = new ArrayList<>();
			queryEstoque.getResultList().forEach(estoque -> {
				produtosPrecoId.add(estoque.getProduto().getId());
			});
			
			jpql.append("AND p.id IN :produtosPrecoId ");
			parameters.put("produtosPrecoId", produtosPrecoId);
		}
		
		if (order.length() != 0) {
			if (order.equals("populares")) {
				
			} else if (order.equals("mais-vendidos")) {
				
			} else if (order.equals("novos")) {
				jpql.append("ORDER BY p.dataCadastro DESC");
			} else if (order.equals("ofertas")) {
				jpql.append("ORDER BY p.estoques.ofertas DESC");
			} else if (order.equals("mais-caro")) {
				jpql.append("ORDER BY p.estoques.preco DESC");
			} else if (order.equals("mais-barato")) {
				jpql.append("ORDER BY p.estoques.preco ASC");
			}
		} else {
			jpql.append("ORDER BY p.id ASC");
		}

		TypedQuery<Produto> query = entityManager.createQuery(jpql.toString(), Produto.class);

		parameters.forEach((key, value) -> query.setParameter(key, value));

		return query.getResultList();

	}

	@Override
	public List<Produto> findLikeDetalhe(String detalhe) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);

		Root<Produto> root = criteriaQuery.from(Produto.class);

		var predicates = new ArrayList<Predicate>();

		String[] detalhes = detalhe.toLowerCase().split("-");

		for (int i = 0; i < detalhes.length; i++) {

			String nome = Normalizer.normalize(detalhes[i], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

			predicates.add(criteriaBuilder.like(
					criteriaBuilder.function("unaccent", String.class, criteriaBuilder.lower(root.get("detalhe"))),
					"%" + nome + "%"));

		}

		criteriaQuery.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Produto> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();

	}

}
