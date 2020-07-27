package mappers;

import models.BrandEntity;
import org.apache.ibatis.annotations.*;

public interface BrandMapper {

    @Select("select * from brands where name=#{name}")
    @Results({
            @Result(id=true, property="name", column="name"),
            @Result(property="originCountry", column="origin")
    })
    BrandEntity getByName(String name);

    @Insert("insert into brands (name,origin) values(#{name},#{originCountry})")
    void create(BrandEntity entity);

    void update(BrandEntity entity);

    void delete(BrandEntity entity);


}
