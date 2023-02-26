package utils;


import java.util.List;

public class ListUtils {

    public static List<?> applyPagination(int offset, List<?> lista) {
        if(lista.size() > offset){
            if(lista.size() - offset >= offset){
                return lista.subList(offset, offset * 2);
            } else {
                return lista.subList(offset, lista.size());
            }
        }
        return lista;
    }
}
