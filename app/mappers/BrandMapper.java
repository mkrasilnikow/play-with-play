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

    @Update("update brands set origin=#{originCountry} where name=#{name}")
    void update(BrandEntity entity);

    @Delete("delete from brands where name=#{name}")
    void delete(String name);

}
