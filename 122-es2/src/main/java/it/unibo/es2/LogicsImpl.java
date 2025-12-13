package it.unibo.es2;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class LogicsImpl implements Logics{

    private final int size;
    private final List<Pair<Integer, Integer>> buttonList;
    private final List<Boolean> boolList;
    private final Pair<Integer, Integer> controllo;
    private final Map<Pair<Integer, Integer>, String> map;
    private final String s = " ";
    private int contatore = 0;
    Boolean bool = true;

    public LogicsImpl(int size) {
        this.size = size;
        this.controllo = new Pair<>(-1, 0);
        this.buttonList = new LinkedList<>();
        this.boolList = new LinkedList<>();
        this.map = new LinkedHashMap<>();

            
    }

    @Override
    public String hit(Pair<Integer, Integer> position) {
        if (map.containsKey(position) && map.get(position) == "*") {
            map.put(position, " ");
            return " "; 
        } else {
            map.put(position, "*");
            return "*";
        }
    }

    //se tutti gli elementi con stessa x o con stessa y sono o " " o "*" fine gioco

    @Override
    public Boolean gameController() {
        //System.out.println(map.entrySet());
        for(this.contatore = 0; contatore < this.size; contatore++ ){
            bool = map.keySet().stream()
            .filter( x -> x.x()==contatore) // filtro tutte le x con stesso valore
            .anyMatch(value -> map.get(value) == " "); // controllo se per il valore key (pair )
            return bool;
        }
        for(this.contatore = 0; contatore < this.size; contatore++ ){
            return map.keySet().stream()
            .filter( x -> x.x()==contatore) // filtro tutte le x con stesso valore
            .anyMatch(value -> map.get(value) == " "); // controllo se per il valore key (pair )
        }
        

        System.out.println("--------------");
        //return false;
    }

}
