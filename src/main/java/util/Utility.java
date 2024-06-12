package util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Utility {

    public static List<Integer> getShuffledItemIndices() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        // Randomly select a subset of 1 to 6 items
        Random rand = new Random();
        int numberOfItems = rand.nextInt(6) + 1; // Ensure at least one item is selected

        return indices.subList(0, numberOfItems);
    }

    // read from Json

    public static String getSingleJsonData(String jsonFilePath, String jsonField) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(jsonField).toString();
    }

    // read from excel

    public static String getExcelData(int RowNum, int ColNum, String SheetName) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String projectPath = System.getProperty("user.dir");
        String cellData = null;
        try {
            workBook = new XSSFWorkbook(projectPath + "/src/test/resources/test_data/data.xlsx");
            sheet = workBook.getSheet(SheetName);
            cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }

    public static String[] readJson(String jsonFilePath, String jsonFieldArray, String field) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get(jsonFieldArray);

        String arr[] = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String fieldData = (String) users.get(field);

            arr[i] = fieldData;
        }
        return arr;
    }

    public static List<Integer> generateUniqueRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public static float parsePriceFromString(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            throw new IllegalArgumentException("Price string cannot be null or empty");
        }

        // Remove leading and trailing whitespaces (optional)
        priceString = priceString.trim();

        // Check if the string starts with a dollar sign ($)
        if (!priceString.startsWith("$")) {
            throw new IllegalArgumentException("Price string must start with a dollar sign ($)");
        }

        // Extract the number part (everything after the dollar sign)
        String numberString = priceString.substring(1);

        // Parse the number string to a float
        try {
            return Float.parseFloat(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format. Please provide a valid number after the dollar sign ($)", e);
        }
    }


    public static void main(String[] args) {
        System.out.println(parsePriceFromString("$9.99"));
    }
}


//    public static HashSet<Integer> generateRandomUniqueIntFrom1To6() {
//        HashSet<Integer> numbers = new HashSet<>();
//        Random random = new Random();
//        while (numbers.size() < 6) {
//            int t=random.nextInt(6) + 1;
//            System.out.println(t);
//            numbers.add(t);
//        }
//        return numbers; // Convert HashSet to List and pick the first element
//    }
//
//    public static void main(String[] args) {
//        System.out.println(generateRandomUniqueIntFrom1To6().size());
//
//        for(int i=0;i<6;i++)
//            System.out.println(new ArrayList<Integer>(generateRandomUniqueIntFrom1To6()));
//    }

