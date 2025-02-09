package teren.mall2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import teren.mall2.domain.Todo;
import teren.mall2.dto.PageRequestDTO;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
