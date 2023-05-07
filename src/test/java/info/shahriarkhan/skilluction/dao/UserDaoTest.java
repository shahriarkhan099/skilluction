package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql", "file:src/test/resources/db/skilluction-db.sql"})
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void testFindByEmail() {
        User user = userDao.findByEmail("instructorUser2@gmail.com");
        long expectedResult = 4;
        assertEquals(expectedResult, user.getUserId());
    }

    @Test
    public void testFindingNonExistingUserWithEmail(){
        User user = userDao.findByEmail("nonExistEmail@gmail.com");
        assertNull(user);
    }
}