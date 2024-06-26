package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.PostDTO;
import by.juanjo.jitter.core.dto.PostDetailsDTO;
import by.juanjo.jitter.core.dto.PostSummaryDTO;
import by.juanjo.jitter.core.dto.minimal.MinimalPostDTO;
import by.juanjo.jitter.core.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class, CommentMapper.class,
    TagMapper.class, UserPostShareMapper.class,
    ReportMapper.class, InteractionMapper.class})
public interface PostMapper {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  public PostSummaryDTO toPostSummaryDTO(Post entity);

  public PostDetailsDTO toPostDetailsDTO(Post entity);

  public MinimalPostDTO toMinimalDTO(Post entity);

  public PostDTO toDTO(Post entity);


  public Post toEntity(PostSummaryDTO dto);

  public Post toEntity(PostDetailsDTO dto);

  public Post toEntity(MinimalPostDTO dto);

  public Post toEntity(PostDTO dto);


}
