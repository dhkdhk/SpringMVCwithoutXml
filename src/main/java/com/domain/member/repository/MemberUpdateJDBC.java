package com.domain.member.repository;


import com.domain.member.entity.Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
//StringUtils

@Repository
public class MemberUpdateJDBC implements MemberUpdateJdbcRepo {

    private final String INSERT_QUERY = "INSERT INTO member (member_password, member_name) VALUES (:memberPassword, :memberName)";
    private final String SELECT_QUERY = "SELECT member_id, member_name FROM member WHERE member_id= :memberId";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberUpdateJDBC(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class MemberMapper implements RowMapper<Member>{
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = Member.builder()
                .memberId(rs.getLong("member_id"))
                .memberName(rs.getString("member_name"))

                    .build();
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

}
