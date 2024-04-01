import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class EdgeConvertCreateDDLTest {

    // Making a mock class to test the EdgeConvertCreateDLL's non abstract methods.
    public class MockEdgeConvertCreateDDL extends EdgeConvertCreateDDL {
        public MockEdgeConvertCreateDDL(EdgeTable[] tables, EdgeField[] fields) {
            super(tables, fields);
        }

        // Overriding getTable and getField returning the super value for testing.
        @Override
        protected EdgeTable getTable(int numFigure) {
            return super.getTable(numFigure);
        }
        @Override
        protected EdgeField getField(int numFigure) {
            return super.getField(numFigure);
        }

        // Not testing these abstract methods.
        @Override
        public String getDatabaseName() { return null; }
        @Override
        public String getProductName() { return null; }
        @Override
        public String getSQLString() { return null; }
        @Override
        public void createDDL() {}
    }

    MockEdgeConvertCreateDDL testDDL;
    EdgeTable table1;
    EdgeTable table2;
    EdgeTable[] tables;
    EdgeField field1;
    EdgeField field2;
    EdgeField[] fields; 

    @Before
    public void setUp() throws Exception {
        table1 = new EdgeTable("1|testTable1");
        table1.addNativeField(1);
        table1.addNativeField(2);
        table1.addNativeField(3);
        table1.addRelatedTable(1);
        table1.makeArrays();
        table1.setRelatedField(0, 1);
        table1.setRelatedField(1, 1);
        table1.setRelatedField(2, 1);

        table2 = new EdgeTable("2|testTable2");
        table2.addNativeField(4);
        table2.addNativeField(5);
        table2.addRelatedTable(2);
        table2.makeArrays();
        table2.setRelatedField(0, 2);
        table2.setRelatedField(1, 2);
        tables = new EdgeTable[]{table1, table2};

        field1 = new EdgeField("1|Field1|0|1|1|true|false");
        field2 = new EdgeField("2|Field2|1|1|1|true|false");
        fields = new EdgeField[]{field1, field2};

        testDDL = new MockEdgeConvertCreateDDL(tables, fields);
    }

    @Test
    public void testInitializeEmptyTables() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        EdgeTable[] tables = new EdgeTable[0];
        EdgeField[] fields = new EdgeField[0];
        MockEdgeConvertCreateDDL mockDLL = new MockEdgeConvertCreateDDL(tables, fields);
        mockDLL.initialize();

        Field maxBoundField = EdgeConvertCreateDDL.class.getDeclaredField("maxBound");
        maxBoundField.setAccessible(true);
        int maxBoundValue = (int) maxBoundField.get(mockDLL);

        Field numBoundTablesField = EdgeConvertCreateDDL.class.getDeclaredField("numBoundTables");
        numBoundTablesField.setAccessible(true);
        int[] numBoundTablesValue = (int[]) numBoundTablesField.get(mockDLL);

        assertEquals("MaxBond values should be 0", 0, maxBoundValue);
        for (int i = 0; i < numBoundTablesValue.length; i++) {
            assertEquals("Table Values should be 0", 0, numBoundTablesValue[i]);
        }
    }

    @Test
    public void testInitialize() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        testDDL.initialize();

        Field maxBoundField = EdgeConvertCreateDDL.class.getDeclaredField("maxBound");
        maxBoundField.setAccessible(true);
        int maxBoundValue = (int) maxBoundField.get(testDDL);

        Field numBoundTablesField = EdgeConvertCreateDDL.class.getDeclaredField("numBoundTables");
        numBoundTablesField.setAccessible(true);
        int[] numBoundTablesValue = (int[]) numBoundTablesField.get(testDDL);

        assertEquals("MaxBound values should be 3", 3, maxBoundValue);
        assertEquals("First table should have 3 related fields", 3, numBoundTablesValue[0]);
        assertEquals("Second table should have 2 related fields", 2, numBoundTablesValue[1]);
    }

    @Test
    public void testGetTable() {
        EdgeTable test1 = testDDL.getTable(1);
        EdgeTable test2 = testDDL.getTable(2);
        EdgeTable test3 = testDDL.getTable(3);
        assertEquals("First table should be equal to the first table in set up", table1, test1);
        assertEquals("Second table should be equal to the second table in set up", table2, test2);
        assertEquals("Third table doesn't exsist, so should equal null", null, test3);
    }

    @Test
    public void testGetField() {
        EdgeField test1 = testDDL.getField(1);
        EdgeField test2 = testDDL.getField(2);
        EdgeField test3 = testDDL.getField(3);
        assertEquals("First field should equal the first field in the set up", field1, test1);
        assertEquals("Second field should equal the second field in the set up",  field2, test2);
        assertEquals("Third field doesn't exist and should equal null", null, test3);
    }
}
