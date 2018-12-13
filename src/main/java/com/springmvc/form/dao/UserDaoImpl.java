package com.springmvc.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.springmvc.form.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM testdb.users  WHERE id=:id";
		User result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params,
					new UserMapper());
		} catch (EmptyResultDataAccessException e) {

		}
		return result;

	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM testdb.users";
		List<User> result = namedParameterJdbcTemplate.query(sql,
				new UserMapper());
		return result;

	}

	@Override
	public void save(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO testdb.users(NAME, EMAIL, loginId, PASSWORD, SEX) VALUES ( :name, :email, :loginId, :password, :sex )";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user),
				keyHolder);
		user.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE testdb.users SET NAME=:name, EMAIL=:email, loginId=:loginId,PASSWORD=:password,SEX=:sex WHERE id=:id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM USERS WHERE id= :id";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id",
				id));

	}

	private SqlParameterSource getSqlParameterByModel(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("loginId", user.getLoginId());
		paramSource.addValue("sex", user.getSex());
		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setLoginId(rs.getString("loginId"));
			user.setSex(rs.getString("sex"));
			return user;
		}
	}

	@SuppressWarnings("unused")
	private static List<String> convertDelimitedStringToList(String delimitedString) {
		List<String> result = new ArrayList<String>();
		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(
					delimitedString, ","));
		}
		return result;

	}

	@SuppressWarnings("unused")
	private String convertListToDelimitedString(List<String> list) {
		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;
	}

	@Override
	public User findByLoginId(String LoginId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("LoginId", LoginId);
		String sql = "SELECT * FROM testdb.users  WHERE LoginId=:LoginId";
		User result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params,
					new UserMapper());
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

}