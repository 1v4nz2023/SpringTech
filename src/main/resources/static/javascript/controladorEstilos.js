
$(document).ready(function() {
    // Agregar efecto de sombreado a las imágenes al pasar el ratón
    $(document).on('mouseenter', '.card-img-top', function() {
        $(this).addClass('image-shadow'); // Agregar clase para sombreado
        $(this).addClass('image-opaque'); // Agregar clase para opacidad
    });

    // Quitar efecto de sombreado a las imágenes al quitar el ratón
    $(document).on('mouseleave', '.card-img-top', function() {
        $(this).removeClass('image-shadow'); // Quitar clase para sombreado
        $(this).removeClass('image-opaque'); // Quitar clase para opacidad
    });

});
