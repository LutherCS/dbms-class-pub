/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Roman
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    mlb.AddressTest.class, 
    mlb.PlayerTest.class, 
    mlb.TeamTest.class, 
    mlb.DatabaseWriterTest.class, 
    mlb.DatabaseWriterDriverTest.class, 
    mlb.DatabaseReaderTest.class, 
//    mlb.DatabaseReaderGUITest.class
})
public class MlbSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
