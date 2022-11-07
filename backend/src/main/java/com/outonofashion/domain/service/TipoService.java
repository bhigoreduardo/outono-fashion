package com.outonofashion.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outonofashion.domain.model.Categoria;
import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.model.Tipo;
import com.outonofashion.domain.repository.CategoriaRepository;
import com.outonofashion.domain.repository.GeneroRepository;

@Service
public class TipoService implements ITipoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Tipo> findByDescricaoAndGenero(String genero, String categoria) {

		// Created Query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> produtoQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> produtoRoot = produtoQuery.from(Produto.class);

		// Created Predicates Where Clause
		List<Predicate> predicates = new ArrayList<Predicate>();

		/*
		 * SELECT t.descricao tipo t INNER JOIN produto p ON t.id = p.tipo_id INNER JOIN
		 * categoria c ON p.categoria_id = c.id INNER JOIN genero g ON p.genero_id =
		 * g.id WHERE c.descricao = 'Calçados' AND g.descricao = 'Feminino';
		 */

		Genero generoObj = generoRepository.findByDescricao(genero).get();

		Categoria categoriaObj = categoriaRepository.findByDescricao(categoria).get();

		predicates.add(criteriaBuilder.equal(produtoRoot.get("genero"), generoObj));
		predicates.add(criteriaBuilder.equal(produtoRoot.get("categoria"), categoriaObj));

		produtoQuery.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Produto> query = entityManager.createQuery(produtoQuery);

		List<Long> categoriaIds = new ArrayList<>();

		for (int i = 0; i < query.getResultList().size(); i++) {
			categoriaIds.add(query.getResultList().get(i).getTipo().getId());
		}

		CriteriaQuery<Tipo> tipoQuery = criteriaBuilder.createQuery(Tipo.class);
		Root<Tipo> tipoRoot = tipoQuery.from(Tipo.class);

		Predicate predicateCategoriaId = tipoRoot.get("id").in(categoriaIds);

		tipoQuery.where(predicateCategoriaId);

		TypedQuery<Tipo> queryTipo = entityManager.createQuery(tipoQuery);

		return queryTipo.getResultList();
	}

}
