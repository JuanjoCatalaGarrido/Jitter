package by.juanjo.jitter.core.mapper;

import by.juanjo.jitter.core.dto.TagDTO;
import by.juanjo.jitter.core.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    PostMapper.class})
public interface TagMapper {

  TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

  public TagDTO toDTO(Tag entity);

  public Tag toEntity(TagDTO dto);

}
