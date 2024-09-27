import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class EdgeTable {
    private static final Logger logger = LogManager.getLogger(EdgeTable.class);

    private int numFigure;
    private String name;
    private ArrayList<Integer> alRelatedTables, alNativeFields;
    private int[] relatedTables, relatedFields, nativeFields;

    public EdgeTable(String inputString) {
        StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
        numFigure = Integer.parseInt(st.nextToken());
        name = st.nextToken();
        alRelatedTables = new ArrayList<>();
        alNativeFields = new ArrayList<>();

        logger.debug("EdgeTable instance created with inputString: {}", inputString);
    }

    public int getNumFigure() {
        logger.info("Getting numFigure: {}", numFigure);
        return numFigure;
    }

    public String getName() {
        logger.info("Getting name: {}", name);
        return name;
    }

    public void addRelatedTable(int relatedTable) {
        logger.debug("Adding related table: {}", relatedTable);
        alRelatedTables.add(relatedTable);
    }

    public int[] getRelatedTablesArray() {
        logger.info("Getting relatedTables array");
        return relatedTables;
    }

    public int[] getRelatedFieldsArray() {
        logger.info("Getting relatedFields array");
        return relatedFields;
    }

    public void setRelatedField(int index, int relatedValue) {
        logger.debug("Setting relatedFields at index {} to: {}", index, relatedValue);
        relatedFields[index] = relatedValue;
    }

    public int[] getNativeFieldsArray() {
        logger.info("Getting nativeFields array");
        return nativeFields;
    }

    public void addNativeField(int value) {
        logger.debug("Adding native field: {}", value);
        alNativeFields.add(value);
    }

    public void moveFieldUp(int index) {
        if (index == 0) {
            return;
        }
        int tempNative = nativeFields[index - 1];
        nativeFields[index - 1] = nativeFields[index];
        nativeFields[index] = tempNative;
        int tempRelated = relatedFields[index - 1];
        relatedFields[index - 1] = relatedFields[index];
        relatedFields[index] = tempRelated;
    }

    public void moveFieldDown(int index) {
        if (index == (nativeFields.length - 1)) {
            return;
        }
        int tempNative = nativeFields[index + 1];
        nativeFields[index + 1] = nativeFields[index];
        nativeFields[index] = tempNative;
        int tempRelated = relatedFields[index + 1];
        relatedFields[index + 1] = relatedFields[index];
        relatedFields[index] = tempRelated;
    }

    public void makeArrays() {
        logger.debug("Converting ArrayLists into int[] arrays");

        Integer[] temp;
        temp = alNativeFields.toArray(new Integer[alNativeFields.size()]);
        nativeFields = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            nativeFields[i] = temp[i].intValue();
        }

        temp = alRelatedTables.toArray(new Integer[alRelatedTables.size()]);
        relatedTables = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            relatedTables[i] = temp[i].intValue();
        }

        relatedFields = new int[nativeFields.length];
        for (int i = 0; i < relatedFields.length; i++) {
            relatedFields[i] = 0;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: ").append(numFigure).append("\r\n");
        sb.append("{\r\n");
        sb.append("TableName: ").append(name).append("\r\n");
        sb.append("NativeFields: ");
        for (int i = 0; i < nativeFields.length; i++) {
            sb.append(nativeFields[i]);
            if (i < (nativeFields.length - 1)) {
                sb.append(EdgeConvertFileParser.DELIM);
            }
        }
        sb.append("\r\nRelatedTables: ");
        for (int i = 0; i < relatedTables.length; i++) {
            sb.append(relatedTables[i]);
            if (i < (relatedTables.length - 1)) {
                sb.append(EdgeConvertFileParser.DELIM);
            }
        }
        sb.append("\r\nRelatedFields: ");
        for (int i = 0; i < relatedFields.length; i++) {
            sb.append(relatedFields[i]);
            if (i < (relatedFields.length - 1)) {
                sb.append(EdgeConvertFileParser.DELIM);
            }
        }
        sb.append("\r\n}\r\n");

        return sb.toString();
    }
}
