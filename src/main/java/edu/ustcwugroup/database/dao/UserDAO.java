package edu.ustcwugroup.database.dao;

import edu.ustcwugroup.database.model.Molecule;
import edu.ustcwugroup.database.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Haozk on 2019/12/26
 */
@Mapper
public interface UserDAO {
    String TABLE_NAME=" user ";
    String INSERT_FIELDS =" name, salt, password, createdDate, status, email";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id = #{id}"})
    User selectByid(@Param("id") int id);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where name = #{name}"})
    User selectByName(@Param("name") String name);

    @Insert({"insert into ", TABLE_NAME," (", INSERT_FIELDS, ")values(#{name},#{salt},#{password},#{createdDate},#{status},#{email})"})
    int addUser(User user);
}
