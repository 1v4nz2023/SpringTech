

// CONTROL DE SOMBRAS Y OPACIDAD DE LAS IMAGENES
$(document).ready(function(){
    // Al pasar el ratón sobre la imagen, agrega sombreado
    $('.card-img-top').mouseenter(function(){
        $(this).addClass('image-shadow'); // Agregar clase para sombreado
        $(this).addClass('image-opaque'); // Agregar clase para opacidad

    });

    // Al quitar el ratón de la imagen, quita el sombreado
    $('.card-img-top').mouseleave(function(){
        $(this).removeClass('image-shadow'); // Quitar clase para sombreado
        $(this).removeClass('image-opaque'); // Quitar clase para opacidad

    });
});