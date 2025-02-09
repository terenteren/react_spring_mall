package teren.mall2.service;

import org.springframework.transaction.annotation.Transactional;
import teren.mall2.domain.Todo;
import teren.mall2.dto.PageRequestDTO;
import teren.mall2.dto.PageResponseDTO;
import teren.mall2.dto.TodoDTO;

@Transactional
public interface TodoService {

    TodoDTO get(Long tno);

    Long register(TodoDTO dto);

    void modify(TodoDTO dto);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    default TodoDTO entityToDTO(Todo todo) {
        return TodoDTO.builder()
                    .tno(todo.getTno())
                    .title(todo.getTitle())
                    .content(todo.getContent())
                    .complete(todo.isComplete())
                    .dueDate(todo.getDueDate())
                    .build();
    }

    default Todo dtoToEntity(TodoDTO todoDTO) {
        return Todo.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .content(todoDTO.getContent())
                .complete(todoDTO.isComplete())
                .dueDate(todoDTO.getDueDate())
                .build();
    }

}
