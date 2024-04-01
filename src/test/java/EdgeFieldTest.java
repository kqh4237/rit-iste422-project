import static org.junit.Assert.*;
import org.junit.Test;

public class EdgeFieldTest {

  @Test
  public void testConstructor() {
      EdgeField edgeField = new EdgeField("1|FieldName");
      assertEquals(1, edgeField.getNumFigure());
      assertEquals("FieldName", edgeField.getName());
      assertEquals(0, edgeField.getTableID());
      assertEquals(false, edgeField.getDisallowNull());
      assertEquals(false, edgeField.getIsPrimaryKey());
      assertEquals("", edgeField.getDefaultValue());
  }

  @Test
  public void testGetters() {
      EdgeField edgeField = new EdgeField("1|FieldName");
      assertEquals(1, edgeField.getNumFigure());
      assertEquals("FieldName", edgeField.getName());
      assertEquals(0, edgeField.getTableBound());
      assertEquals(0, edgeField.getFieldBound());
      assertFalse(edgeField.getDisallowNull());
      assertFalse(edgeField.getIsPrimaryKey());
      assertEquals("", edgeField.getDefaultValue());
      assertEquals(EdgeField.VARCHAR_DEFAULT_LENGTH,         
      edgeField.getVarcharValue());
  }

  @Test
  public void testSetters() {
    EdgeField edgeField = new EdgeField("1|FieldName|1|1|1|true|false");
    edgeField.setTableID(2);
    assertEquals(2, edgeField.getTableID());
    edgeField.setTableBound(3);
    assertEquals(3, edgeField.getTableBound());
    edgeField.setFieldBound(4);
    assertEquals(4, edgeField.getFieldBound());
    edgeField.setDisallowNull(false);
    assertFalse(edgeField.getDisallowNull());
    edgeField.setIsPrimaryKey(true);
    assertTrue(edgeField.getIsPrimaryKey());
    edgeField.setDefaultValue("TestDefault");
    assertEquals("TestDefault", edgeField.getDefaultValue());
    edgeField.setVarcharValue(5);
    assertEquals(5, edgeField.getVarcharValue());
    edgeField.setDataType(2);
    assertEquals(2, edgeField.getDataType());
  }

  @Test
  public void testBoundaryConditions() {
    EdgeField edgeField = new EdgeField("1|FieldName|1|1|1|true|false");
    edgeField.setTableID(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, edgeField.getTableID());
    edgeField.setVarcharValue(255);
    assertEquals(255, edgeField.getVarcharValue());
    edgeField.setDataType(3);
    assertEquals(3, edgeField.getDataType());
  }

  @Test
  public void testToString() {
      EdgeField edgeField = new EdgeField("1|FieldName");
      String expected = "1|FieldName|0|0|0|0|1|false|false|";
      assertEquals(expected.trim(), edgeField.toString().trim());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetVarcharValue() {
    EdgeField edgeField = new EdgeField("1|FieldName|1|1|1|true|false");
    edgeField.setVarcharValue(-1);
  }
}
