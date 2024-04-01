import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
public class CreateDDLMySQLTest {

    @Test
    public void testSingleTable() {
        EdgeField[] fields = {
            new EdgeField("1|field1"),
            new EdgeField("2|field2"),
            new EdgeField("3|field3"),
            new EdgeField("4|field4")
        };
        // Sets field to data type DEFAULT= VARCHAR 1= BOOL 2= INT 3= DOUBLE
        fields[0].setVarcharValue(100);
        fields[1].setDataType(1);
        fields[2].setDataType(2);
        fields[3].setDataType(3);

        EdgeTable[] tables = { new EdgeTable("1|Table1") };
        tables[0].addNativeField(1);
        tables[0].addNativeField(2);
        tables[0].addNativeField(3);
        tables[0].addNativeField(4);

        tables[0].makeArrays(); 

        CreateDDLMySQL ddlGenerator = new CreateDDLMySQL(tables, fields);


        String expectedDDL = "CREATE DATABASE MySQLDB;\r\nUSE MySQLDB;\r\n" +
            "CREATE TABLE Table1 (\r\n" +
            "\tfield1 VARCHAR(100),\r\n" +
            "\tfield2 BOOL,\r\n" +
            "\tfield3 INT,\r\n" +
            "\tfield4 DOUBLE,\r\n" +
            ");\r\n\r\n";
        assertEquals(expectedDDL, ddlGenerator.getSQLString());
    }

    @Test
    public void testMultipleTablesWithRelationships() {

        EdgeTable table1 = new EdgeTable("1|Table1");
        EdgeTable table2 = new EdgeTable("2|Table2");

        EdgeField field1 = new EdgeField("1|Field1");
        EdgeField field2 = new EdgeField("2|Field2");



        table1.addNativeField(1);
        table2.addNativeField(2);



        field1.setTableID(1);
        field2.setTableID(2);

        field2.setTableBound(1);

        table2.addRelatedTable(1);
        table1.addRelatedTable(2);



field1.setIsPrimaryKey(true);


         EdgeTable[] tables = {table1, table2};

        EdgeField[] fields ={field1, field2};

        tables[0].makeArrays(); 
        tables[1].makeArrays();  
        table2.setRelatedField(0, 0);
        // Create CreateDDLMySQL object with sample tables and fields
        CreateDDLMySQL ddlGenerator = new CreateDDLMySQL(tables, fields);

        // Generate the SQL string

        String generatedSQL = ddlGenerator.getSQLString();

        // Expected SQL string
        String expectedSQL = "CREATE DATABASE MySQLDB;\r\n" +
                             "USE MySQLDB;\r\n" +
                             "CREATE TABLE Table1 (\r\n" +
                             "\tField1 VARCHAR(1),\r\n" +
                             "CONSTRAINT Table1_PK PRIMARY KEY (Field1)\r\n" +
                             ");\r\n" +
                             "\r\n" +
                             "CREATE TABLE Table2 (\r\n" +
                             "\tField2 VARCHAR(1),\r\n" +
                             "\tCONSTRAINT Table2_FK1 FOREIGN KEY(Field2) REFERENCES Table1(Field1)\r\n" +
                             ");\r\n" +
                             "\r\n";

        // Compare the generated SQL with the expected SQL
        assertEquals(expectedSQL, generatedSQL);
    }

}
