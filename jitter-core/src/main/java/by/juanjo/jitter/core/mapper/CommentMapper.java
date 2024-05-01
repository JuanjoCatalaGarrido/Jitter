package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.CommentDTO;
import by.juanjo.jitter.core.entity.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    UserMapper.class, PostMapper.class})
public interface CommentMapper {

  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  public CommentDTO toDTO(Comment entity);

  public Comment toEntity(CommentDTO dto);

}
