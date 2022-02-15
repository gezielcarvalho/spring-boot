package info.gezielcarvalho.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.gezielcarvalho.demo.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		final String sql = "INSERT INTO Person (id,name) VALUES (?,?)";
		return jdbcTemplate.update(sql, new Object[]{id,person.getName()});
	}

	@Override
	public List<Person> selectAllPeople() {
		final String sql = "SELECT id, name FROM Person";
		List<Person> result = jdbcTemplate.query(sql,(rs, rowNum) -> {
			UUID id = UUID.fromString(rs.getString("id"));
			String name = rs.getString("name");
			return new Person(id,name);
		});
		return result;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		final String sql = "SELECT id,name FROM Person WHERE id=?";
		Person person = jdbcTemplate.queryForObject(
				sql,
				(rs, rowNum) -> {
					UUID personId = UUID.fromString(rs.getString("id"));
					String name = rs.getString("name");
					return new Person(personId,name);
				},
				new Object[]{id});
		return Optional.ofNullable(person);
	}

	@Override
	public int deletePersonById(UUID id) {
		final String sql = "DELETE FROM Person WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		final String sql = "UPDATE Person SET name=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{person.getName(),id});
	}

}
