import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parseJackson(String json) {
        return this.parseJackson(json, Integer.MAX_VALUE);
    }

    public List<Map<String, String>> parseJackson(String json, Integer limite){
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> retorno = new ArrayList<>();
        int count = 1;
        try {
            JsonNode raiz = mapper.readTree(json);

            JsonNode items = raiz.path("items");
            if(items.isArray()){
                for(JsonNode no : items){
                    Map<String, String> dados = new HashMap<>();
                    no.fieldNames().forEachRemaining(name -> dados.put(name, no.get(name).asText()));
                    retorno.add(dados);
                    if(++count > limite) {
                        break;
                    }
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao processar o JSON "+e.toString());
        }
        return retorno;
    }

    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;

    }

}
