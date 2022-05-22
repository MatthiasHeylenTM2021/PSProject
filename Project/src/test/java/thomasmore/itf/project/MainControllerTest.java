package thomasmore.itf.project;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;
import thomasmore.itf.project.controller.MainController;

import java.io.IOException;


@SpringBootTest
public class MainControllerTest extends TestCase {

    @Mock
    private Model model;

    @Autowired
    MainController mainController;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void showIndexPageTest() {
        Assertions.assertEquals("index", mainController.index(model));
    }

    @Test
    public void resultPageTest() throws IOException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader("from", "EUR");
        mockRequest.addHeader("to", "USD");
        mockRequest.addHeader("amount", "5");
        Throwable e = null;
        try {
            mainController.result(mockRequest, model);
        } catch (IOException ex) {
           e = ex;
        }

        Assertions.assertTrue(e instanceof IOException);

    }

}
