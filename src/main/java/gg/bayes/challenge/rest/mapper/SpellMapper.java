package gg.bayes.challenge.rest.mapper;

import gg.bayes.challenge.rest.entity.HeroSpell;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpellMapper {


    HeroSpells map(HeroSpell item);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<HeroSpells> map(List<HeroSpell> herospell);
}
