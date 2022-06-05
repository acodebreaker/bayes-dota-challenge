package gg.bayes.challenge.rest.mapper;

import gg.bayes.challenge.rest.entity.Item;
import gg.bayes.challenge.rest.model.HeroItems;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    HeroItems map(Item item);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<HeroItems> map(List<Item> items);


}
