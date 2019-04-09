import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout ="/templates/layout.vtl";

        get("/", (request,response)-> {
            Map<String,Object> model = new HashMap<>();
            model.put("template","templates/coin_form.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/result", (request,response)-> {
            Map<String,Object> model = new HashMap<>();
            model.put("template","templates/result.vtl");
            ChangeMachine changeMachine = new ChangeMachine();
            String change = changeMachine.makeChange(getFloat(request.queryParams("amount")));
            model.put("amount",change);
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());
    }
    private static float getFloat(String number)
    {
        return Float.parseFloat(number);

    }
}
