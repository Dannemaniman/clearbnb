import express.Express;

import static nosqlite.Database.collection;

public class Main {
    public static void main(String[] args) {
        Express app = new Express();

        collection(config -> {
            config.useBrowser = true;
        });

        app.get("/hello", (req, res) -> {
            res.send("Hello from Express");
        });






        app.listen(4000);
    }
}
