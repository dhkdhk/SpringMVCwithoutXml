package com.domain.member.repository.jdbc;


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
public class MemberManagementJDBC implements MemberManagementJdbcRepo {

    private final String INSERT_QUERY = "INSERT INTO members(memberName, memberPassword, memberEmail, memberAge, memberSex, memberAddress, memberPhoneNumber, memberGrade) " +
            "                           VALUES(:memberName, :memberPassword, :memberEmail, :memberAge, :memberSex, :memberAddress, :memberPhoneNumber, :memberGrade)";
    private final String SELECT_QUERY = "SELECT * FROM members WHERE memberId= :memberId";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberManagementJDBC(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class MemberMapper implements RowMapper<Member> {
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = Member.builder()
                    .memberId(rs.getLong("memberId"))
                    .memberName(rs.getString("memberName"))
                    .memberPassword(rs.getString("memberPassword"))
                    .memberEmail(rs.getString("memberEmail"))
                    .memberAge(rs.getInt("memberAge"))
                    .memberSex(rs.getString("memberSex"))
                    .memberAddress(rs.getString("memberAddress"))
                    .memberPhoneNumber(rs.getString("memberPhoneNumber"))
                    .memberGrade(rs.getString("memberGrade"))

                    .build();
            return member;
        }
    }


    @Override
    public int addMemeber(Member member) {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("memberName", member.getMemberName());
        paramMap.put("memberPassword", member.getMemberPassword());
        paramMap.put("memberEmail", member.getMemberEmail());
        paramMap.put("memberAge", member.getMemberAge());
        paramMap.put("memberSex", member.getMemberSex());
        paramMap.put("memberAddress", member.getMemberAddress());
        paramMap.put("memberPhoneNumber", member.getMemberPhoneNumber());
        paramMap.put("memberGrade", member.getMemberGrade());

        return jdbcTemplate.update(INSERT_QUERY, paramMap);
    }


    @Override
    public Member getMember(Long memberId) {
        Map namedParameters = new HashMap();
        namedParameters.put("memberId", memberId);
        return jdbcTemplate.queryForObject(SELECT_QUERY, namedParameters, new MemberMapper());
    }

}
