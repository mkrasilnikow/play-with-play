package mappers;

import models.ModelEntity;
import models.ProductItemEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;

public interface ProductMapper {

    @Select("select * from products where id=#{id}")
    @Results({
            @Result(id=true, property="id", column="id"),
            @Result(property="brand", column="brand"),
            @Result(property="model", column="model"),
            @Result(property="yearOfProduction", column="startYear"),
            @Result(property="mileage", column="mileage"),
            @Result(property="price", column="price", javaType = BigDecimal.class)
    })
    ProductItemEntity getById(int id);

    @Insert("insert into products (brand,model,startYear,mileage,price) values(#{brand},#{model},#{yearOfProduction},#{mileage},#{price})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer create(ProductItemEntity entity);

    @Update("update products set brand=#{brand},model=#{model},startYear=#{yearOfProduction},mileage=#{mileage},price=#{price} where id=#{id}")
    void update(ProductItemEntity entity);

    @Delete("delete from products where id=#{id}")
    void delete(int id);

}
