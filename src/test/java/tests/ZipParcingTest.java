package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ZipParcingTest {
    private ClassLoader cl = ZipParcingTest.class.getClassLoader();

    String sourceName = "src.zip";

    @Test
    @DisplayName("Проверка пустой ли архив")
    void zipIsEmpty() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream(sourceName)
        )) {
            ZipEntry entry = zis.getNextEntry();
            assertNotNull(entry, "Архив пустой");
        }
    }


    @Test
    @DisplayName("Тест pdf из архива")
    void zipPDFParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream(sourceName)
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals("Evgeny", pdf.author);
                }
            }
        }
    }

    @Test
    @DisplayName("Тест xls из архива")
    void zipXLSParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream(sourceName)
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Основные параметры чтения"));
                    Double numValue = xls.excel.getSheetAt(0).getRow(1).getCell(0).getNumericCellValue();
                    Assertions.assertTrue(numValue == 1);
                    actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Рекомендовано"));
                }
            }
        }
    }

    @Test
    @DisplayName("Тест csv из архива")
    void zipCSVParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream(sourceName); ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(3, data.size());
                    Assertions.assertArrayEquals(new String[]{"Кошка", " Мурка"}, data.get(0));
                }
            }
        }
    }

}
