package HTTPServer;

import java.io.Closeable;
import java.io.IOException;

public class Connection implements Runnable, Closeable {
    private Connectable socket;
    private Setup settings;
    private DataStorage dataStore;
    private Router2 router;

    public Connection(Connectable socket, Router2 router) {
        this.socket = socket;
        this.router = router;
    }

    public Connection(Connectable socket) {
        this(socket, new Setup(), new DataStore());
    }

    public Connection(Connectable socket, Setup settings, DataStorage dataStore) {
        this.socket = socket;
        this.settings = settings;
        this.dataStore = dataStore;
    }

    public void run() {
        Request request = read();
//        Handler handler = new BasicHandler(router);
        Response response = null;
        try {
//            response = handler.handle(request);
            response = router.route(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        write(response);
        close();
    }

    public Request read() {
        Request request = null;
        try {
            request = new RequestParser().parse(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void write(Response response) {
        try {
            socket.getOutputStream().write(new ResponseFormatter().formatResponse(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
