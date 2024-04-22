import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeConnectorTest {
  EdgeConnector testObj;

  @Before
  public void setUp() throws Exception {
    testObj = new EdgeConnector("1|2|3|testStyle1|testStyle2");
  }

  @Test
  public void testGetNumConnector() {
    // Example of how a value can be passed into a test
    String opt1Str = System.getProperty("optionone");
    final long opt1 = opt1Str == null ? 1 : Long.parseLong(opt1Str);
    assertEquals("numConnector was initialized to 1 so it should be 1", opt1, testObj.getNumConnector());
  }

  @Test
  public void testGetEndPoint1() {
    assertEquals("EndPoint1 was initialized to 2", 2, testObj.getEndPoint1());
  }

  @Test
  public void testGetEndPoint2() {
    assertEquals("EndPoint2 was initialized as 3", 3, testObj.getEndPoint2());
  }

  @Test
  public void testGetEndStyle1() {
    assertEquals("endStyle1 was initialized to \"testStyle1\"", "testStyle1", testObj.getEndStyle1());
  }

  @Test
  public void testGetEndStyle2() {
    assertEquals("endStyle2 was initialized to \"testStyle2\"", "testStyle2", testObj.getEndStyle2());
  }

  @Test
  public void testGetIsEP1Field() {
    assertFalse("isEP1Field should be false", testObj.getIsEP1Field());
  }

  @Test
  public void testGetIsEP2Field() {
    assertFalse("isEP2Field should be false", testObj.getIsEP2Field());
  }

  @Test
  public void testGetIsEP1Table() {
    assertFalse("isEP1Table should be false", testObj.getIsEP1Table());
  }

  @Test
  public void testGetIsEP2Table() {
    assertFalse("isEP2Table should be false", testObj.getIsEP2Table());
  }

  @Test
  public void testSetIsEP1Field() {
    testObj.setIsEP1Field(false);
    assertFalse("isEP1Field should be what you set it to", testObj.getIsEP1Field());
  }

  @Test
  public void testSetIsEP2Field() {
    testObj.setIsEP2Field(false);
    assertFalse("isEP2Field should be what you set it to", testObj.getIsEP2Field());
  }

  @Test
  public void testSetIsEP1Table() {
    testObj.setIsEP1Table(false);
    assertFalse("isEp1Table should be what you set it to", testObj.getIsEP1Table());
  }

  @Test
  public void testSetIsEP2Table() {
    testObj.setIsEP2Table(false);
    assertFalse("isEp2Table should be what you set it to", testObj.getIsEP2Table());
  }
}

// Removed unnecessary blank lines for better readability
// Simplified the initialization of `opt1` variable in the testGetNumConnector
// method
// Used assertFalse/assertTrue instead of assertEquals for boolean assertions