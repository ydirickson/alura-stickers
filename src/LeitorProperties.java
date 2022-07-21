import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LeitorProperties {
    
    private Properties props;

    private List<Map<String, String>> endpoints = new ArrayList<>();

    private String apiKey;

    private boolean classificar;

    public LeitorProperties() throws Exception{
        this.props = new Properties();
        try(InputStream fis = new FileInputStream("opcoes.properties")) {
            this.props.load(fis);
            this.apiKey = this.props.getProperty("api.key");
            this.classificar = "true".equals(this.props.getProperty("classificar"));
            lerEndpoints();
        }
    }

    public void lerEndpoints() {
        Map<String, String> top250Props = new HashMap<>();
        top250Props.put("nome", this.props.getProperty("endpoints.top250movies.nome"));
        top250Props.put("url", this.props.getProperty("endpoints.top250movies.url"));
        top250Props.put("alturl", this.props.getProperty("endpoints.top250movies.alturl"));
        top250Props.put("limite", this.props.getProperty("endpoints.top250movies.limite"));
        endpoints.add(top250Props);
    }

    public List<Map<String, String>> getEndpoints() {
        return endpoints;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public boolean getClassificar(){
        return this.classificar;
    }

}
