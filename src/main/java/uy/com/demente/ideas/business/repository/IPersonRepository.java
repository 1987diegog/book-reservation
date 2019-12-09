package uy.com.demente.ideas.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.com.demente.ideas.model.Person;

public interface IPersonRepository extends JpaRepository<Person, Long> {

}
