package com.domain.repository;


import com.domain.entity.Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String INSERT_QUERY = "INSERT INTO member (member_password, member_name) VALUES (:memberPassword, :memberName)";
    private final String SELECT_QUERY = "SELECT member_id, member_name FROM member WHERE member_id= :memberId";
    private final String UPDATE_QUERY = "UPDATE member SET member_name= :memberName";
    private final String DELETE_QUERY = "DELETE member WHERE member_id= :memberId";

    public MemberRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class MemberMapper implements RowMapper<Member>{
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberId(rs.getLong("member_id"));
            member.setMemberName(rs.getString("member_name"));
            return member;
        }
    }


    @Override
    public int addMemeber(Member member) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("memberPassword", member.getMemberPassword());
        paramMap.put("memberName", member.getMemberName());

        return jdbcTemplate.update(INSERT_QUERY, paramMap);
    }



    @Override
    public Member getMember(Long memberId) {
        Map namedParameters = new HashMap();
        namedParameters.put("memberId",memberId);
        return jdbcTemplate.queryForObject(SELECT_QUERY, namedParameters, new MemberMapper());
    }

    @Override
    public void updateMember() {

    }



    @Override
    public void deleteMember() {

    }
}
