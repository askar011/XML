import com.askar.xml.util.Constant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.askar.xml.util.validator.ValidatorXSD;

public class ValidationTest {

    ValidatorXSD validator = null;

    @Before
    public void setUp() throws Exception {
        validator = new ValidatorXSD();
    }

    @After
    public void tearDown() throws Exception {
        validator = null;
    }

    @Test
    public void checkParser() {
        boolean expected = true;
        boolean actual = validator.validate(Constant.XMLFILE_PATH);
        Assert.assertEquals(expected, actual);
    }
}
