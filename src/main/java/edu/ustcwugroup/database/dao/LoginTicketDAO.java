package edu.ustcwugroup.database.dao;

import edu.ustcwugroup.database.model.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * Created by Haozk on 2019/11/6
 */
@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME = "loginTicket";
    String INSERT_FIELDS = " userid, expired, status, ticket ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket ticket);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

}
