package tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import model.Update;
import model.UpdateData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonTest {
    private final ClassLoader classLoader = JsonTest.class.getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Парсинг json файла update")
    void jsonParsingTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("update.json")) {
            assertNotNull(inputStream, "Файл не найден!");

            Update upate = mapper.readValue(inputStream, Update.class);

            // проверки update:
            assertEquals(180196452, upate.getFpCheckEntityKey());

            List<UpdateData> upData = upate.getUpdateData();
            assertEquals(1, upData.size());
            // проверки updateData:
            UpdateData updateData = upData.get(0);
            assertEquals(180196453, updateData.getCheckKey());
            assertEquals("checkGlass1", updateData.getCheckCode());
            assertEquals(49, updateData.getCclAttrKey());
            assertEquals(999999, updateData.getCclAttrResultKey());
            assertEquals(1, updateData.getValue());
            assertEquals(1, updateData.getResult());


        }
    }
}
