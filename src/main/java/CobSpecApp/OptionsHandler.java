package CobSpecApp;

import HTTPServer.*;
import HTTPServer.Handlers.Handler;

public class OptionsHandler implements Handler {
    private Settings settings;
    private Repository dataStore;

    public OptionsHandler(Settings settings) {
        this.settings = settings;
    }

    public OptionsHandler(Settings settings, Repository dataStore) {
        this.settings = settings;
        this.dataStore = dataStore;
    }

    public Response handle(Request request) {
        return new Response(200);
    }
}
