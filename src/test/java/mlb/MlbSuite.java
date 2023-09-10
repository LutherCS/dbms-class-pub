package mlb;

/**
 * @author Roman Yasinovskyy
 */

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import java.io.File;

@Suite
@SuiteDisplayName("MLB Test Suite")
@SelectPackages("mlb")
@IncludeClassNamePatterns(".*Test")
public class MlbSuite {

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
        File db_file = new File("data/mlb/test.sqlite");

        if (db_file.exists()) {
            db_file.delete();
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

}
