import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest{

    EdgeTable testObj;

    @Before
    public void setUp() throws Exception{
    testObj = new EdgeTable("1|testTable");
  }

    @Test
    public void testGetNumFigure(){
        assertEquals("Num Figure expected to be 1", 1, testObj.getNumFigure());
    }

    @Test
    public void testGetName(){
        assertEquals("Name expected to be 'testTable'", "name", testObj.getName());
    }

    @Test
    public void testGetRelatedTablesArray(){
        assertEquals("Related Table Array should be empty", null , testObj.getRelatedTablesArray());
    }

    @Test
    public void testgetRelatedFieldsArray(){
        assertEquals("Related Field Array should be empty", null , testObj.getRelatedFieldsArray());
    }

    @Test
    public void testSetRelatedFieldsArray(){
        testObj.setRelatedField(0, 5);
        int[] testArray = new int[]{5};
        assertArrayEquals("Arrays should be identical",testArray, testObj.getRelatedFieldsArray());
    }

    @Test
    public void testMakeArrays(){
        testObj.addRelatedTable(3);
        testObj.addRelatedTable(4);
        testObj.addRelatedTable(5);
        testObj.addNativeField(6);
        testObj.addNativeField(7);
        testObj.addNativeField(8);
        testObj.makeArrays();
        int[] testRelTabArray = new int[] {3, 4, 5};
        int[] testNatArray = new int[] {6, 7, 8};
        int[] testRelFieldArray = new int[] {0, 0, 0};
        assertArrayEquals("Arrays should be identical",testRelTabArray, testObj.getRelatedTablesArray());
        assertArrayEquals("Arrays should be identical",testNatArray, testObj.getNativeFieldsArray());
        assertArrayEquals("Arrays should be identical",testRelFieldArray, testObj.getRelatedFieldsArray());
    }

}