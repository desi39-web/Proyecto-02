public class Analizador {

    public void empezarBusqueda(Documento[] todosLosDocs, String consulta, ArbolAVL<Documento> arbol) {
        for (Documento doc : todosLosDocs) {
            double similitud = calcularSimilitud(doc, consulta, todosLosDocs);
            if (similitud > 0) {
                doc.setPrioridad(similitud);
                arbol.agregar(doc);
            }
        }
    }

    private double calcularSimilitud(Documento doc, String consulta, Documento[] repositorio) {
    	String consultaLimpia = consulta.toLowerCase().replaceAll("\\p{Punct}", "");
        String[] terminosConsulta = consultaLimpia.split("\\s+");
        String contenidoDoc = Lector.leerArchivo(doc.getRuta()); 
    	String contenidoLimpio = contenidoDoc.toLowerCase().replaceAll("\\p{Punct}", "");

   		 double numerador = 0;
    		 double denominador = 1.0; 

    	for (String termino : terminosConsulta) {
        	if (!termino.isEmpty()) {
           	 double tf = calcularTF(termino, contenidoLimpio);
           	 double idf = calcularIDF(termino, repositorio);
            		numerador += (tf * idf);
        	}
    	}

    	return numerador / denominador; 
    }

    private double calcularTF(String termino, String contenido) {
        String[] palabras = contenido.split("\\s+");
        int count = 0;
        for (String p : palabras) {
            if (p.equals(termino)) {
                count++;
            }
        }
        return count == 0 ? 0 : (Math.log(count) / Math.log(2)) + 1;
    }

    private double calcularIDF(String termino, Documento[] repositorio) {
            int docsConTermino = 0;
    
    	for (Documento d : repositorio) {
        	String contenidoDoc = Lector.leerArchivo(d.getRuta());
                String contenidoLimpio = contenidoDoc.toLowerCase().replaceAll("[^a-z ]", "");
        
        	if (buscarPalabraExacta(contenidoLimpio, termino)) {
            	docsConTermino++;
        }
    }
    
    return Math.log((double) (repositorio.length + 1) / (docsConTermino + 1)) / Math.log(2);
    
    }

	private boolean buscarPalabraExacta(String texto, String objetivo) {
    		String[] palabras = texto.split("\\s+");
    		for (String p : palabras) {
        		if (p.equals(objetivo)) return true;
    		}
    		return false;
	}
}
