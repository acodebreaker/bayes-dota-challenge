package gg.bayes.challenge.rest.mapper;

import gg.bayes.challenge.rest.entity.Damage;
import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroKills;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DamageMapper {

    HeroDamage map(Damage damage);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<HeroDamage> map(List<Damage> damages);
}
