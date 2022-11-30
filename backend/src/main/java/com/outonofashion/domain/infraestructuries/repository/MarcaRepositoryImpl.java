package com.outonofashion.domain.infraestructuries.repository;

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
import org.springframework.stereotype.Repository;

import com.outonofashion.domain.model.Genero;
import com.outonofashion.domain.model.Marca;
import com.outonofashion.domain.model.Produto;
import com.outonofashion.domain.repository.GeneroRepository;
import com.outonofashion.domain.repository.MarcaRepositoryQueries;

@Repository
public class MarcaRepositoryImpl implements MarcaRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GeneroRepository generoRepository;

	@Override
	public List<Marca> findByGenero(String genero) {

		// Created Query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> produtoQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> produtoRoot = produtoQuery.from(Produto.class);

		// Created Predicates Where Clause
		List<Predicate> predicates = new ArrayList<Predicate>();

		Genero generoObj = generoRepository.findByDescricao(genero).get();

		predicates.add(criteriaBuilder.equal(produtoRoot.get("genero"), generoObj));

		produtoQuery.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Produto> query = entityManager.createQuery(produtoQuery);

		List<Long> marcaIds = new ArrayList<>();

		for (int i = 0; i < query.getResultList().size(); i++) {
			marcaIds.add(query.getResultList().get(i).getMarca().getId());
		}

		CriteriaQuery<Marca> marcaQuery = criteriaBuilder.createQuery(Marca.class);
		Root<Marca> marcaRoot = marcaQuery.from(Marca.class);

		Predicate predicateMarcaId = marcaRoot.get("id").in(marcaIds);

		marcaQuery.where(predicateMarcaId);

		TypedQuery<Marca> queryMarca = entityManager.createQuery(marcaQuery);

		return queryMarca.getResultList();
	}

}
