package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.CommentDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalCommentDTO;
import by.juanjo.jitter.core.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class, PostMapper.class})
public interface CommentMapper {

  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  public CommentDTO toDTO(Comment entity);

  public MinimalCommentDTO toMinimalCommentDTO(Comment entity);

  public Comment toEntity(CommentDTO dto);

  public Comment toEntity(MinimalCommentDTO dto);

}
