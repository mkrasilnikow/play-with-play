package transformers;

import dto.Brand;
import models.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandTransformer {
    BrandTransformer INSTANCE = Mappers.getMapper(BrandTransformer.class);
    Brand map(BrandEntity source);
}
