package mappers;

import models.BrandEntity;
import models.ModelEntity;
import org.apache.ibatis.annotations.*;

public interface ModelMapper {

    @Select("select * from models where name=#{name}")
    @Results({
            @Result(id=true, property="name", column="name"),
            @Result(property="yearOfProductionStart", column="startYear"),
            @Result(property="yearOfProductionEnd", column="endYear")
    })
    ModelEntity getByName(String name);

    @Insert("insert into models (name,startYear,endYear) values(#{name},#{yearOfProductionStart},#{yearOfProductionEnd})")
    void create(ModelEntity entity);

    @Update("update models set startYear=#{yearOfProductionStart}, endYear=#{yearOfProductionEnd} where name=#{name}")
    void update(ModelEntity entity);

    @Delete("delete from models where name=#{name}")
    void delete(String name);

}
