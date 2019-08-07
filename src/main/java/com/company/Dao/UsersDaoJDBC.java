package com.company.Dao;

import com.company.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UsersDaoJDBC implements UsersDao {

    JdbcTemplate template;
    String dbName;
    //language=SQL
    private String SaveUser="INSERT INTO users(name, birthdate, usermail, hashpass) VALUES (?, ?, ?, ?);";
    //language=SQL
    private String FindUser="SELECT * FROM users WHERE usermail=?;";
    //language=SQL
    private String MaxId="SELECT MAX(id) FROM users;";
    //language=SQL
    private String FindAll="SELECT * FROM users";

    private RowMapper<User> rowMapper = (ResultSet resultSet, int i)->{
        User user = new User(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("hashpass"),
                LocalDate.parse(resultSet.getString("birthdate")),
                resultSet.getString("usermail")
                );
        return user;
    };
    private RowMapper<Integer> idRowMapper = (ResultSet resultser, int id)->{
        Integer mid = resultser.getInt(1);
        return mid;
    };


    public UsersDaoJDBC(DataSource dataSource){
        template = new JdbcTemplate(dataSource);

    }

    @Override
    public boolean Save(User obj) {


        if(this.findbymail(obj.getUserMail()).isEmpty()){

            template.update(SaveUser, obj.getName(), obj.getBirthdate().toString(), obj.getUserMail(), obj.getPassword());
            return true;
        }
        else return false;
    }

    @Override
    public boolean Update(User obj) {
        return true;
    }

    @Override
    public void Delete(Integer id) {

    }

    @Override
    public Optional<User> find(String usermail, String password) {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        List<User> users = template.query(FindUser, rowMapper, usermail);
        if(users.isEmpty() || !BCrypt.checkpw(password, users.get(0).getPassword()))
            return Optional.empty();
        else return Optional.of(users.get(0));
    }
    @Override
    public Optional<User> findbymail(String usermail) {
        List<User> users = template.query(FindUser, rowMapper, usermail);
        if(users.isEmpty())
            return Optional.empty();
        else return Optional.of(users.get(0));
    }

    public Integer MaxId(){
        List<Integer> mid = template.query(MaxId, idRowMapper);
        return mid.get(0);
    }

}
