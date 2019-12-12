package edu.ustcwugroup.database.dao;

import edu.ustcwugroup.database.model.Molecule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Haozk on 2019/12/12
 */
@Mapper
public interface MoleculeDAO {
    String TABLE_NAME="data ";
    String INSERT_FIELDS =" elements, formula";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    List<Molecule> selectCules(@Param("culeIds") List<Integer> culeIds,
                               @Param("count") int count);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id = #{id}"})
    Molecule selectMolecule(@Param("id") int id);

    @Update({"update ",TABLE_NAME," set elements=#{elements},formula=#{formula} where id = #{id}"})
    int updateMolucule(@Param("elements") String elements,
                       @Param("formula") String formula,
                       @Param("id") int id);

}
