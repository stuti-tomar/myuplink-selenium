package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

 

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

/**
* Utility class for working with Excel files.
*/
public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

 

    /**
     * Set the Excel file to work with.
     *
     * @param Path      The path of the Excel file.
     * @param SheetName The name of the sheet in the Excel file.
     * @throws Exception If an error occurs while setting the Excel file.
     */
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

 

    /**
     * Read data from the specified cell in the Excel file.
     *
     * @param RowNum The row number of the cell.
     * @param ColNum The column number of the cell.
     * @return The data in the specified cell as a string.
     * @throws Exception If an error occurs while reading the cell data.
     */
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

 

    /**
     * Write data to the specified cell in the Excel file.
     *
     * @param Result  The data to write to the cell.
     * @param RowNum  The row number of the cell.
     * @param ColNum  The column number of the cell.
     * @throws Exception If an error occurs while writing the cell data.
     */
    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            // Save the changes to the Excel file
            FileOutputStream fileOut = new FileOutputStream(Constant.CONS_TEST_DATA);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw (e);
        }
    }

 

    public static int getRowCount() {
        return 0;
    }

 

    public static int getColumnCount() {
        return 0;
    }
}