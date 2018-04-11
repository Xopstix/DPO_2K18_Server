package config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by xaviamorcastillo on 12/4/18.
 */
public class ObjectFile {

    //Classe usada per a la lectura del fitxer json
    public ObjectFile (){

    }

    public Config readData () throws FileNotFoundException {

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader("src/config.json"));
        Config config = gson.fromJson(jsonReader, Config.class);

        return config;

    }

}
