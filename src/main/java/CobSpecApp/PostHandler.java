package CobSpecApp;

import HTTPServer.*;

public class PostHandler implements Handler {
    private Setup settings;
    private DataStorage dataStore;

    public PostHandler(Setup settings) {
        this.settings = settings;
    }

    public PostHandler(Setup settings, DataStorage dataStore) {
        this.settings = settings;
        this.dataStore = dataStore;
    }

    public Response handle(Request request) {
        return new Response(200);
    }
}
