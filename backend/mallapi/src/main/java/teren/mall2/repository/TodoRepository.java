package teren.mall2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teren.mall2.domain.Todo;
import teren.mall2.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}
