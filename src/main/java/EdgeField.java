import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringTokenizer;

public class EdgeField {
    private static final Logger logger = LogManager.getLogger(EdgeField.class);

    private int numFigure, tableID, tableBound, fieldBound, dataType, varcharValue;
    private String name, defaultValue;
    private boolean disallowNull, isPrimaryKey;
    private static String[] strDataType = {"Varchar", "Boolean", "Integer", "Double"};
    public static final int VARCHAR_DEFAULT_LENGTH = 1;

    public EdgeField(String inputString) {
        StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
        numFigure = Integer.parseInt(st.nextToken());
        name = st.nextToken();
        tableID = 0;
        tableBound = 0;
        fieldBound = 0;
        disallowNull = false;
        isPrimaryKey = false;
        defaultValue = "";
        varcharValue = VARCHAR_DEFAULT_LENGTH;
        dataType = 0;

        logger.debug("EdgeField instance created with inputString: {}", inputString);
    }

    public int getNumFigure() {
        logger.info("Getting numFigure: {}", numFigure);
        return numFigure;
    }

    public String getName() {
        logger.info("Getting name: {}", name);
        return name;
    }

    public int getTableID() {
        logger.info("Getting tableID: {}", tableID);
        return tableID;
    }

    public void setTableID(int value) {
        logger.debug("Setting tableID to: {}", value);
        tableID = value;
    }

    public int getTableBound() {
        logger.info("Getting tableBound: {}", tableBound);
        return tableBound;
    }

    public void setTableBound(int value) {
        logger.debug("Setting tableBound to: {}", value);
        tableBound = value;
    }

    public int getFieldBound() {
        logger.info("Getting fieldBound: {}", fieldBound);
        return fieldBound;
    }

    public void setFieldBound(int value) {
        logger.debug("Setting fieldBound to: {}", value);
        fieldBound = value;
    }

    public boolean getDisallowNull() {
        logger.info("Getting disallowNull: {}", disallowNull);
        return disallowNull;
    }

    public void setDisallowNull(boolean value) {
        logger.debug("Setting disallowNull to: {}", value);
        disallowNull = value;
    }

    public boolean getIsPrimaryKey() {
        logger.info("Getting isPrimaryKey: {}", isPrimaryKey);
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean value) {
        logger.debug("Setting isPrimaryKey to: {}", value);
        isPrimaryKey = value;
    }

    public String getDefaultValue() {
        logger.info("Getting defaultValue: {}", defaultValue);
        return defaultValue;
    }

    public void setDefaultValue(String value) {
        logger.debug("Setting defaultValue to: {}", value);
        defaultValue = value;
    }

    public int getVarcharValue() {
        logger.info("Getting varcharValue: {}", varcharValue);
        return varcharValue;
    }

    public void setVarcharValue(int value) {
        if (value > 0) {
            logger.debug("Setting varcharValue to: {}", value);
            varcharValue = value;
        }
    }

    public int getDataType() {
        logger.info("Getting dataType: {}", dataType);
        return dataType;
    }

    public void setDataType(int value) {
        if (value >= 0 && value < strDataType.length) {
            logger.debug("Setting dataType to: {}", value);
            dataType = value;
        }
    }

    public static String[] getStrDataType() {
        logger.debug("Getting strDataType");
        return strDataType;
    }

    public String toString() {
        return numFigure + EdgeConvertFileParser.DELIM +
                name + EdgeConvertFileParser.DELIM +
                tableID + EdgeConvertFileParser.DELIM +
                tableBound + EdgeConvertFileParser.DELIM +
                fieldBound + EdgeConvertFileParser.DELIM +
                dataType + EdgeConvertFileParser.DELIM +
                varcharValue + EdgeConvertFileParser.DELIM +
                isPrimaryKey + EdgeConvertFileParser.DELIM +
                disallowNull + EdgeConvertFileParser.DELIM +
                defaultValue;
    }
}