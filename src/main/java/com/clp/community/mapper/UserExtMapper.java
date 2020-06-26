package com.clp.community.mapper;

import com.clp.community.model.User;
import com.clp.community.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserExtMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

//    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
//    void insert(User user);
//
//    @Select("select * from user where token = #{token}")
//    User findByToken(@Param("token") String token);
//
//    @Select("select * from user where id = #{id}")
//    User findById(@Param("id") Integer id);
//
//    @Select("select * from user where account_id = #{accountId}")
//    User findByAccountId(@Param("accountId") String accountId);
//
//    @Update("update user set name = #{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id = #{id}")
//    void update(User dbUser);
}