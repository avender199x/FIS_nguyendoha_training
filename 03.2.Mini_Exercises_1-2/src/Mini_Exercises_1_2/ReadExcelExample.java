package Mini_Exercises_1_2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadExcelExample {
    public static final int COLUMN_Transaction_Type = 0;
    public static final int COLUMN_Bank_Account = 1;
    public static final int COLUMN_Amount = 2;
    public static final int COLUMN_Message = 3;
    public static final int COLUMN_Date_Time = 4;

    public static void main(String[] args) throws IOException, ParseException {
        final String excelFilePath = "transaction_history.xlsx";
        final List<transaction> books = readExcel(excelFilePath);
        for (transaction book : books) {
            System.out.println(book);
        }
    }

    public static List<transaction> readExcel(String excelFilePath) throws IOException, ParseException {
        List<transaction> listBooks = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            transaction transaction = new transaction();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_Transaction_Type:
                        transaction.setTransaction_Type((String) getCellValue(cell));
                        break;
                    case COLUMN_Bank_Account:
                        transaction.setBank_Account((String) getCellValue(cell));
                        break;
                    case COLUMN_Message:
                        transaction.setMessage((String) getCellValue(cell));

                        break;
                    case COLUMN_Amount:
                        transaction.setAmount((Double) getCellValue(cell));
                        break;
                    case COLUMN_Date_Time:
                        String time = (String) getCellValue(cell);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Date date = simpleDateFormat.parse(time);

                        transaction.setDateTime(date);
                        break;
                    default:
                        break;
                }

            }
            listBooks.add(transaction);
        }

        workbook.close();
        inputStream.close();

        return listBooks;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
