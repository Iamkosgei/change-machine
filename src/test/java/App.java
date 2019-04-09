import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        get("/", (request,response)-> "Hello Friend");
    }
}
