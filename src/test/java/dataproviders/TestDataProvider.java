
package dataproviders;

import org.testng.annotations.DataProvider;
import readers.JsonDataReader;

import java.util.List;
import java.util.Map;

public class TestDataProvider {

    @DataProvider(name = "webFormTestData", parallel = true)
    public static Object[][] getWebFormTestData() {

        List<Map<String, Object>> testData =
                JsonDataReader.readJson("testdata/webFormData.json");

        Object[][] data = new Object[testData.size()][1];

        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }

        return data;
    }
}
