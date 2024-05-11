$(document).ready(function() {

    let rol = $('#rol').text();

    console.log(rol);
    
var isLoggedIn = localStorage.getItem('isLoggedIn');

// Obtener la URL actual
var url = window.location.href;

// Buscar la posición de "/usuario" en la URL
var index = url.indexOf(rol);

// Si se encuentra "/usuario" en la URL
if (index !== -1) {
    // Guardar la parte de la URL que viene después de "/usuario"
    var rolusuario = url.substring(index);
    console.log(rolusuario);
} else {
    console.log("La URL actual no contiene '/usuario'.");
}


    if(!isLoggedIn || rol!=rolusuario){
        localStorage.clear();

        
      window.location.href = "logout";

    }


}

















)