package org.midbel.psutil;

import spark.ResponseTransformer;
import com.google.gson.Gson;

public class GsonTransformer implements ResponseTransformer {
    
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return this.gson.toJson(model);
    }
}
