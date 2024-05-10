$(document).ready(function() {
    var logoutBtn = $('#btn-logout');
    var loginBtn = $('#btn-login');
    var listaopciones = $('#listaopciones');


    var isLoggedIn = localStorage.getItem('isLoggedIn');
    if (isLoggedIn === 'true') {
        loginBtn.hide();

        $('#listaopciones').removeClass('d-none');
        $('#listaopciones').addClass('d-block');
        
    } else {
        listaopciones.hide();
        loginBtn.show();
    }

    logoutBtn.on('click', function() {
        localStorage.clear();
        
    });


// Manejo del evento storage para detectar cambios en el nombre de usuario
window.addEventListener('storage', function(event) {
    // Verificar si la clave que ha cambiado es la clave de usuario
    if (event.key === 'isLoggedIn') {
        const nuevoUsuario = localStorage.getItem('isLoggedIn');
        // Verificar si el nuevo usuario es diferente al usuario actual
        if (nuevoUsuario !== isLoggedIn) {
            // Realizar alguna acción cuando el nombre de usuario cambie, por ejemplo, actualizar la página
            localStorage.clear();
            window.location.href = 'logout';        }
    }
});


});
