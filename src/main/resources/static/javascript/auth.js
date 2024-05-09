$(document).ready(function() {
    var logoutBtn = $('#btn-logout');
    var loginBtn = $('#btn-login');
    var listaopciones = $('#listaopciones');
    listaopciones.hide();


    var isLoggedIn = localStorage.getItem('isLoggedIn');
    var nombreUsuario = localStorage.getItem('nombres');
    var apellidosUsuario = localStorage.getItem('apellidos');

    $('#title').html(`${nombreUsuario} ${apellidosUsuario}`);



    if (isLoggedIn === 'true') {
        loginBtn.hide();
        listaopciones.show();

    } else {
        listaopciones.hide();
        loginBtn.show();
    }

    logoutBtn.on('click', function() {
        localStorage.clear();
        
    });






});
