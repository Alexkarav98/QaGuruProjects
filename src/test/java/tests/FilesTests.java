package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import schemas.JsonSchema;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTests {

    private String pathZip ="src/test/resources/files.zip";
    ClassLoader classLoader = getClass().getClassLoader();
    ZipFile zipFile;

    {
        try {
            zipFile = new ZipFile(pathZip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled   // NPE
    void ZipTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream(pathZip);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry);
            }
        }
    }

    @Test
    void pdfTest() throws Exception {
        ZipEntry zipEntry = zipFile.getEntry("junit-user-guide-5.8.2.pdf");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        PDF pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("JUnit 5 User Guide");
    }

    @Test
    void csvTest() throws Exception {
        ZipEntry zipEntry = zipFile.getEntry("business-financial-data-sep-2021-quarter.csv");
        try (InputStream inputStream = zipFile.getInputStream(zipEntry);
             CSVReader csv = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> content = csv.readAll();
            assertThat(content.get(16)).contains("BDCQ.SF1AA2CA", "2020.03", "Business Data Collection - BDC");
        }
    }

    @Test
    void xlsTest() throws Exception {
        ZipEntry zipEntry = zipFile.getEntry("Check List.xlsx");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        XLS xls = new XLS(inputStream);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(2)
                .getCell(2)
                .getStringCellValue()).contains("Yandex Browser v 70.1");
    }

    @Test
    void jsonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchema jsonSchema = objectMapper.readValue(Paths.get("src/test/resources/json.json").toFile(), JsonSchema.class);
        assertThat(jsonSchema.someText).isEqualTo("someText");
    }

}
