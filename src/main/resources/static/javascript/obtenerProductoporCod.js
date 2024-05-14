$(document).ready(function() {

    const obtenerProductoPorPartNumber = async () => {
        try {
            const url = window.location.href;
            const partNumber = obtenerValorDespuesDelIgual(url);
            const response = await fetch(`http://localhost:8090/api/productos/${partNumber}`);
            if (!response.ok) {
                throw new Error(`No se pudo obtener el producto con número de parte ${partNumber}`);
            }
            const producto = await response.json();
            console.log(producto); 
        } catch (error) {
            console.error(error);
            window.location.href = "/index";

        }       
    };

    // Función para obtener el valor después del igual en una URL
    const obtenerValorDespuesDelIgual = (url) => {
        const igualIndex = url.indexOf('=');
        if (igualIndex !== -1) {
            return url.substring(igualIndex + 1);
        }
        return null;
    };
      
    // Llamada a la función para obtener información sobre el producto con número de parte específico
    obtenerProductoPorPartNumber();

});
