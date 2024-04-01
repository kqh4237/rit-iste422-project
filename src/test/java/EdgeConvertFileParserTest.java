import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EdgeConvertFileParserTest {
    EdgeConvertFileParser parser;

    EdgeTable table1;
    EdgeTable table2;
    EdgeTable table13;
    EdgeTable[] tables;
    EdgeField field1;
    EdgeField field2;
    EdgeField field3;
    EdgeField field4;
    EdgeField field5;
    EdgeField field6;
    EdgeField field7;
    EdgeField[] fields;

    @Before
    public void setUp() throws Exception {
        // Create mock Edge Diagrammer file
        File testFile = new File("test.edg");
        testFile.createNewFile();

        table1 = new EdgeTable("1|TTable1");
        table1.addNativeField(7);
        table1.addNativeField(8);
        table1.makeArrays();
        table1.setRelatedField(0, 0);
        table1.setRelatedField(1, 0);

        table2 = new EdgeTable("2|TTable2");
        table2.addNativeField(11);
        table2.addNativeField(6);
        table2.addRelatedTable(13);
        table2.makeArrays();
        table2.setRelatedField(0, 0);
        table2.setRelatedField(1, 0);

        table13 = new EdgeTable("13|TTable13");
        table13.addNativeField(3);
        table13.addNativeField(5);
        table13.addRelatedTable(2);
        table13.makeArrays();
        table13.setRelatedField(0, 0);
        table13.setRelatedField(1, 0);

        tables = new EdgeTable[]{table1, table2, table13};

        // field1 = new EdgeField("3|TField1|13|0|0|0|1|false|false");
        // field2 = new EdgeField("4|TField2|0|0|0|0|1|false|false");
        // field3 = new EdgeField("5|TField3|13|0|0|0|1|false|false");
        // field4 = new EdgeField("6|TField4|2|0|0|0|1|false|false");
        // field5 = new EdgeField("7|TField5|1|0|0|0|1|false|false");
        // field6 = new EdgeField("8|TField6|1|0|0|0|1|false|false");
        // field7 = new EdgeField("11|TField7|2|0|0|0|1|false|false");
        // fields = new EdgeField[]{field1, field2, field3, field4, field5, field6, field7};

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(testFile, false)));
        // Write the identification line
        pw.println(EdgeConvertFileParser.SAVE_ID);
        pw.println("#Tables#");
        for (EdgeTable table : tables) {
            pw.println(table.toString());
        }
        pw.println("#Fields#");
        // for (EdgeField field : fields) {
        //     pw.println(field.toString());     
        // }

        // write the fields to the file manually because the other way is not working
        pw.println("3|TField1|13|0|0|0|1|false|false|");
        pw.println("4|TField2|0|0|0|0|1|false|false|");
        pw.println("5|TField3|13|0|0|0|1|false|false|");
        pw.println("6|TField4|2|0|0|0|1|false|false|");
        pw.println("7|TField5|1|0|0|0|1|false|false|");
        pw.println("8|TField6|1|0|0|0|1|false|false|");
        pw.println("11|TField7|2|0|0|0|1|false|false|");

        pw.close();

        // Initialize parser with mock file
        parser = new EdgeConvertFileParser(testFile);
        parser.openFile(testFile);
    }

    @Test
    public void testOpenFile() {
        assertNotNull("EdgeTables array should not be null", parser.getEdgeTables());
        assertNotNull("EdgeFields array should not be null", parser.getEdgeFields());

        assertEquals("EdgeTables array should have 6 elements", 6, parser.getEdgeTables().length);
        assertEquals("EdgeFields array should have 14 elements", 14, parser.getEdgeFields().length);
    }

    @Test
    public void testParseEdgeFile() throws IOException {
        EdgeTable[] tables = parser.getEdgeTables();
        EdgeField[] fields = parser.getEdgeFields();
        assertNotNull("EdgeTables array should not be null after parsing edge file", tables);
        assertNotNull("EdgeFields array should not be null after parsing edge file", fields);
        assertTrue("At least one table should be parsed", tables.length > 0);
        assertTrue("At least one field should be parsed", fields.length > 0);

        assertEquals("EdgeTables array should have 6 elements", 6, tables.length);
        assertEquals("EdgeFields array should have 14 elements", 14, fields.length);

        String[] expectedTableNames = {"TTable1", "TTable2", "TTable13", "TTable1", "TTable2", "TTable13"};
        String[] expectedFieldNames = {"TField1", "TField2", "TField3", "TField4", "TField5", "TField6", "TField7", "TField1", "TField2", "TField3", "TField4", "TField5", "TField6", "TField7"};

        for (int i = 0; i < tables.length; i++) {
            assertEquals("Table name should match", expectedTableNames[i], tables[i].getName());
        }
        for (int i = 0; i < fields.length; i++) {
            assertEquals("Field name should match", expectedFieldNames[i], fields[i].getName());
        }
    }

    @Test
    public void testParseSaveFile() throws IOException {
        EdgeTable[] tables = parser.getEdgeTables();
        EdgeField[] fields = parser.getEdgeFields();
        assertNotNull("EdgeTables array should not be null after parsing edge file", tables);
        assertNotNull("EdgeFields array should not be null after parsing edge file", fields);
        assertTrue("At least one table should be parsed", tables.length > 0);
        assertTrue("At least one field should be parsed", fields.length > 0);

        assertEquals("EdgeTables array should have 6 elements", 6, tables.length);
        assertEquals("EdgeFields array should have 14 elements", 14, fields.length);

        String[] expectedTableNames = {"TTable1", "TTable2", "TTable13", "TTable1", "TTable2", "TTable13"};
        String[] expectedFieldNames = {"TField1", "TField2", "TField3", "TField4", "TField5", "TField6", "TField7", "TField1", "TField2", "TField3", "TField4", "TField5", "TField6", "TField7"};

        for (int i = 0; i < tables.length; i++) {
            assertEquals("Table name should match", expectedTableNames[i], tables[i].getName());
        }
        for (int i = 0; i < fields.length; i++) {
            assertEquals("Field name should match", expectedFieldNames[i], fields[i].getName());
        }
    }

    @Test
    public void testGetEdgeTables() {
        EdgeTable[] tables = parser.getEdgeTables();
        assertNotNull("EdgeTables array should not be null", tables);
        assertTrue("At least one table should be parsed", tables.length > 0);
    }

    @Test
    public void testGetEdgeFields() {
        EdgeField[] fields = parser.getEdgeFields();
        assertNotNull("EdgeFields array should not be null", fields);
        assertTrue("At least one field should be parsed", fields.length > 0);
    }

    // resolveConnectors() is a private method and cannot be tested directly
    // makeArrays() is a private method and cannot be tested directly
    // isTableDup() is a private method and cannot be tested directly

    // use reflection to test private methods
    @Test
    public void testResolveConnectors() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        Method method = EdgeConvertFileParser.class.getDeclaredMethod("resolveConnectors");
        method.setAccessible(true);

        File edgeFile = new File("test.edg"); // Provide path to your test Edge Diagrammer file
        parser.openFile(edgeFile);

        // Get the parsed tables and fields
        EdgeTable[] tables = parser.getEdgeTables();
        EdgeField[] fields = parser.getEdgeFields();
        assertNotNull("EdgeTables array should not be null", tables);
        assertNotNull("EdgeFields array should not be null", fields);

        assertEquals("EdgeTables array should have 9 elements", 9, tables.length);
        assertEquals("EdgeFields array should have 21 elements", 21, fields.length);

        // Check if each table has any related fields
        boolean allTablesHaveRelatedFields = true;
        for (EdgeTable table : tables) {
            boolean hasRelatedFields = false;
            for (EdgeField field : fields) {
                if (field.getTableID() == table.getNumFigure()) {
                    hasRelatedFields = true;
                    break;
                }
            }
            if (!hasRelatedFields) {
                allTablesHaveRelatedFields = false;
                break;
            }
        }

        assertTrue("All tables should have at least one related field, indicating connectors have been resolved", allTablesHaveRelatedFields);
    }

    @Test
    public void testMakeArrays() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = EdgeConvertFileParser.class.getDeclaredMethod("makeArrays");
        method.setAccessible(true);

        File edgeFile = new File("test.edg"); // Provide path to your test Edge Diagrammer file
        parser.openFile(edgeFile);

        EdgeTable[] tables = parser.getEdgeTables();
        EdgeField[] fields = parser.getEdgeFields();

        assertNotNull("EdgeTables array should not be null before calling makeArrays()", tables);
        assertNotNull("EdgeFields array should not be null before calling makeArrays()", fields);

        method.invoke(parser);

        tables = parser.getEdgeTables();
        fields = parser.getEdgeFields();

        assertNotNull("EdgeTables array should not be null after calling makeArrays()", tables);
        assertNotNull("EdgeFields array should not be null after calling makeArrays()", fields);
    }


    @Test
    public void testIsTableDup() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = EdgeConvertFileParser.class.getDeclaredMethod("isTableDup", String.class);
        method.setAccessible(true);

        File edgeFile = new File("test.edg"); // Provide path to your test Edge Diagrammer file
        parser.openFile(edgeFile);

        boolean isTable1Dup = (boolean)method.invoke(parser, "TTable1");
        boolean isTable2Dup = (boolean)method.invoke(parser, "TTable2");
        boolean isTable13Dup = (boolean)method.invoke(parser, "TTable13");

        assertTrue("Table 1 should be detected as duplicate", isTable1Dup);
        assertTrue("Table 2 should be detected as duplicate", isTable2Dup);
        assertTrue("Table 13 should be detected as duplicate", isTable13Dup);
    }
}
