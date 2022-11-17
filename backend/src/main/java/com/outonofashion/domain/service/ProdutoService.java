package com.outonofashion.domain.service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.exception.produto.ProdutoNaoEncontradoException;
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
import com.outonofashion.domain.repository.ProdutoRepository;
import com.outonofashion.domain.repository.TamanhoRepository;
import com.outonofashion.domain.repository.TipoRepository;

@Service
public class ProdutoService implements IProdutoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ProdutoRepository produtoRepository;

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
	
	public Produto findById(Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}

	@Override
	public List<Produto> findProdutos(String[] categoria, String[] tipo, String[] genero, String[] tamanho,
			String[] marca, String[] cor, String precoMin, String precoMax) {

		// Created Query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> produtoQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> produtoRoot = produtoQuery.from(Produto.class);

		// Created Predicates Where Clause
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (categoria.length != 0) {
			// Join Get Categoria Reference in Produto Model
			Join<Produto, Categoria> produtoCategoriaJoin = produtoRoot.join("categoria");

			// Get Id Categoria Join Produto
			Path<Long> categoriaId = produtoCategoriaJoin.get("id");

			// Storage Id Categoria Search
			List<Long> categoriaIds = new ArrayList<>();

			for (int i = 0; i < categoria.length; i++) {
				if (categoriaRepository.findByDescricao(categoria[i]).isEmpty())
					continue;

				Categoria categoriaSearch = categoriaRepository.findByDescricao(categoria[i]).get();

				categoriaIds.add(categoriaSearch.getId());
			}

			// Created CategoriaId Search Predicate
			Predicate predicateCategoriaId = criteriaBuilder.isTrue(categoriaId.in(categoriaIds));

			// Added CategoriaId Search Predicate Into Predicates List
			predicates.add(predicateCategoriaId);
		}

		if (tipo.length != 0) {

			Join<Produto, Tipo> produtoTipoJoin = produtoRoot.join("tipo");

			Path<Long> tipoId = produtoTipoJoin.get("id");

			List<Long> tipoIds = new ArrayList<>();

			for (int i = 0; i < tipo.length; i++) {
				if (tipoRepository.findByDescricao(tipo[i]).isEmpty())
					continue;

				Tipo tipoSearch = tipoRepository.findByDescricao(tipo[i]).get();

				tipoIds.add(tipoSearch.getId());
			}

			Predicate predicateTipoId = criteriaBuilder.isTrue(tipoId.in(tipoIds));

			predicates.add(predicateTipoId);
		}

		if (genero.length != 0) {

			Join<Produto, Genero> produtoGeneroJoin = produtoRoot.join("genero");

			Path<Long> generoId = produtoGeneroJoin.get("id");

			List<Long> generoIds = new ArrayList<>();

			for (int i = 0; i < genero.length; i++) {
				if (generoRepository.findByDescricao(genero[i]).isEmpty())
					continue;

				Genero generoSearch = generoRepository.findByDescricao(genero[i]).get();

				generoIds.add(generoSearch.getId());
			}

			Predicate predicateGeneroId = criteriaBuilder.isTrue(generoId.in(generoIds));

			predicates.add(predicateGeneroId);
		}

		if (marca.length != 0) {

			Join<Produto, Marca> produtoMarcaJoin = produtoRoot.join("marca");

			Path<Long> marcaId = produtoMarcaJoin.get("id");

			List<Long> marcaIds = new ArrayList<>();

			for (int i = 0; i < marca.length; i++) {
				if (marcaRepository.findByDescricao(marca[i]).isEmpty())
					continue;

				Marca marcaSearch = marcaRepository.findByDescricao(marca[i]).get();

				marcaIds.add(marcaSearch.getId());
			}

			Predicate predicateMarcaId = criteriaBuilder.isTrue(marcaId.in(marcaIds));

			predicates.add(predicateMarcaId);
		}

		if (tamanho.length != 0) {

			CriteriaQuery<Estoque> estoqueQuery = criteriaBuilder.createQuery(Estoque.class);
			Root<Estoque> estoqueRoot = estoqueQuery.from(Estoque.class);

			Join<Estoque, Tamanho> estoqueTamanhoJoin = estoqueRoot.join("tamanho");

			Path<Long> tamanhoId = estoqueTamanhoJoin.get("id");

			List<Long> tamanhoIds = new ArrayList<>();

			for (int i = 0; i < tamanho.length; i++) {
				if (tamanhoRepository.findByDescricao(tamanho[i]).isEmpty())
					continue;

				Tamanho tamanhoSearch = tamanhoRepository.findByDescricao(tamanho[i]).get();

				tamanhoIds.add(tamanhoSearch.getId());
			}

			Predicate predicateTamanhoId = criteriaBuilder.isTrue(tamanhoId.in(tamanhoIds));

			estoqueQuery.where(predicateTamanhoId);

			TypedQuery<Estoque> estoqueTypedQuery = entityManager.createQuery(estoqueQuery);

			if (!estoqueTypedQuery.getResultList().isEmpty()) {

				List<Long> produtoIds = new ArrayList<>();

				for (int i = 0; i < estoqueTypedQuery.getResultList().size(); i++) {
					Estoque estoqueSearch = estoqueTypedQuery.getResultList().get(i);

					if (produtoIds.contains(estoqueSearch.getProduto().getId()))
						continue;

					produtoIds.add(estoqueSearch.getProduto().getId());
				}

				predicates.add(produtoRoot.get("id").in(produtoIds));

			} else {

				// Gambiarra
				predicates.add(produtoRoot.get("nome").in("nenhum"));

			}

		}

		if (cor.length != 0) {

			CriteriaQuery<Estoque> estoqueQuery = criteriaBuilder.createQuery(Estoque.class);
			Root<Estoque> estoqueRoot = estoqueQuery.from(Estoque.class);

			Join<Estoque, Cor> estoqueCorJoin = estoqueRoot.join("cor");

			Path<Long> corId = estoqueCorJoin.get("id");

			List<Long> corIds = new ArrayList<>();

			for (int i = 0; i < cor.length; i++) {
				if (corRepository.findByDescricao(cor[i]).isEmpty())
					continue;

				Cor corSearch = corRepository.findByDescricao(cor[i]).get();

				corIds.add(corSearch.getId());
			}

			Predicate predicateCorId = criteriaBuilder.isTrue(corId.in(corIds));

			estoqueQuery.where(predicateCorId);

			TypedQuery<Estoque> estoqueTypedQuery = entityManager.createQuery(estoqueQuery);

			if (!estoqueTypedQuery.getResultList().isEmpty()) {

				List<Long> produtoIds = new ArrayList<>();

				for (int i = 0; i < estoqueTypedQuery.getResultList().size(); i++) {
					Estoque estoqueSearch = estoqueTypedQuery.getResultList().get(i);

					if (produtoIds.contains(estoqueSearch.getProduto().getId()))
						continue;

					produtoIds.add(estoqueSearch.getProduto().getId());
				}

				predicates.add(produtoRoot.get("id").in(produtoIds));

			} else {

				// Gambiarra
				predicates.add(produtoRoot.get("nome").in("nenhum"));

			}

		}

		if (precoMin.length() != 0 && precoMax.length() != 0) {

			BigDecimal precoMinimo = new BigDecimal(precoMin);
			BigDecimal precoMaximo = new BigDecimal(precoMax);

			CriteriaQuery<Estoque> estoqueQuery = criteriaBuilder.createQuery(Estoque.class);
			Root<Estoque> estoqueRoot = estoqueQuery.from(Estoque.class);

			Join<Estoque, Produto> estoqueProdutoJoin = estoqueRoot.join("produto");

			Predicate precoPredicate = criteriaBuilder.between(estoqueRoot.get("preco"), precoMinimo, precoMaximo);

			estoqueQuery.where(precoPredicate);

			TypedQuery<Estoque> estoqueTypedQuery = entityManager.createQuery(estoqueQuery);

			if (!estoqueTypedQuery.getResultList().isEmpty()) {

				List<Long> produtoIds = new ArrayList<>();

				for (int i = 0; i < estoqueTypedQuery.getResultList().size(); i++) {
					Estoque estoqueSearch = estoqueTypedQuery.getResultList().get(i);

					if (produtoIds.contains(estoqueSearch.getProduto().getId()))
						continue;

					produtoIds.add(estoqueSearch.getProduto().getId());
				}

				predicates.add(produtoRoot.get("id").in(produtoIds));

			} else {
				
				// Gambiarra
				predicates.add(produtoRoot.get("nome").in("nenhum"));
				
			}

		}

		produtoQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Produto> query = entityManager.createQuery(produtoQuery);
		
		//Integer totalRows = query.getResultList().size();
		
		//query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		//query.setMaxResults(pageable.getPageSize());
		
		//Page<Produto> result = new PageImpl<Produto>(query.getResultList(), pageable, totalRows);
		//Page<Produto> result = new PageImpl<>(query.getResultList());

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

	public Produto findByNomeAndId(String nome, Long produtoId) {

		return produtoRepository.findByNomeAndId(nome, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(nome, produtoId));

	}

}
