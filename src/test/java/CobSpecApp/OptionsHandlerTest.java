package CobSpecApp;

import HTTPServer.Request;
import HTTPServer.Response;
import HTTPServer.Setup;
import junit.framework.TestCase;

import java.util.HashMap;

public class OptionsHandlerTest extends TestCase {
    public void test_it_returns_a_listing_of_the_methods_that_will_work_on_a_resource() throws Exception {
        HashMap params = new HashMap();
        params.put("path", "/method_options");
        OptionsHandler handler = new OptionsHandler(new Setup(new String[0]));

        Response response = handler.handle(new Request(params));

        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\n", new String(response.getHeader(), "UTF-8"));
    }

    public void test_it_returns_different_results_if_a_file_is_not_there() throws Exception {
        HashMap params = new HashMap();
        params.put("path", "/method_options2");
        OptionsHandler handler = new OptionsHandler(new Setup(new String[0]));

        Response response = handler.handle(new Request(params));

        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,OPTIONS\r\n", new String(response.getHeader(), "UTF-8"));
    }
}
