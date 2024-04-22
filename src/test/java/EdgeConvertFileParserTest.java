import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EdgeConvertFileParserTest {
  EdgeConvertFileParser parser;

  EdgeTable table1;
  EdgeTable table2;
  EdgeTable table13;

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

    EdgeTable[] tables = { table1, table2, table13 };

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(testFile, false)));
    // Write the identification line
    pw.println(EdgeConvertFileParser.SAVE_ID);
    pw.println("#Tables#");
    for (EdgeTable table : tables) {
      pw.println(table.toString());
    }
    pw.println("#Fields#");
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
  }

  @Test
  public void testOpenFile() {
    assertNotNull("EdgeTables array should not be null", parser.getEdgeTables());
    assertNotNull("EdgeFields array should not be null", parser.getEdgeFields());

    assertEquals("EdgeTables array should have 3 elements", 3, parser.getEdgeTables().length);
    assertEquals("EdgeFields array should have 7 elements", 7, parser.getEdgeFields().length);
  }

  @Test
  public void testParseEdgeFile() throws IOException {
    EdgeTable[] tables = parser.getEdgeTables();
    EdgeField[] fields = parser.getEdgeFields();
    assertNotNull("EdgeTables array should not be null after parsing edge file", tables);
    assertNotNull("EdgeFields array should not be null after parsing edge file", fields);
    assertTrue("At least one table should be parsed", tables.length > 0);
    assertTrue("At least one field should be parsed", fields.length > 0);

    assertEquals("EdgeTables array should have 3 elements", 3, tables.length);
    assertEquals("EdgeFields array should have 7 elements", 7, fields.length);

    String[] expectedTableNames = { "TTable1", "TTable2", "TTable13" };

    assertEquals("Expected table names array length should match tables array length", expectedTableNames.length,
        tables.length);

    for (int i = 0; i < tables.length; i++) {
      assertEquals("Table name should match", expectedTableNames[i], tables[i].getName());
    }
  }

  @Test
  public void testParseSaveFile() throws IOException {
    testParseEdgeFile();
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
}

// Simplified setup
// Fixed double counting due to opening file
// Renamed testParseSaveFile to testParseEdgeFile
// Removed duplicate testParseEdgeFile