import { urlServer } from "./url.js";

$(document).ready(function(){

    let id = $('#idUsuario').text();
    let idUsuario =parseInt(id);

    console.log(idUsuario);

$(editar).click(function(){
    editarUsuario(idUsuario);

});
// Función para manejar la edición de un usuario
async function editarUsuario(idUsuario) {
    // Obtener los detalles del usuario por su ID
    const usuario = await obtenerUsuarioPorId(idUsuario);
  
    // Verificar si se obtuvieron los detalles del usuario correctamente
    if (usuario) {
      // Mostrar el modal de edición con los detalles del usuario
      mostrarModalEdicion(usuario);
    }
  }
  


// Función para obtener los detalles de un usuario por su ID
async function obtenerUsuarioPorId(id) {
  try {
    // Realizar una solicitud GET a la ruta /api/usuarios/{id}
    const response = await axios.get(`${urlServer}/api/usuarios/${id}`);

    // Devolver los datos de la respuesta
    return response.data;
  } catch (error) {
    // Manejar errores si la solicitud falla
    if (error.response && error.response.status === 404) {
      // Si el usuario no se encuentra, mostrar un mensaje
      console.error("Usuario no encontrado");
    } else {
      console.error("Error al obtener el usuario:", error);
    }
    return null;
  }
}

// Función para mostrar el modal de edición
function mostrarModalEdicion(usuario) {
    // Obtener el modal de edición
    const modal = document.getElementById("modal-update");
  
    // Llenar el modal con los detalles del usuario
    modal.innerHTML = `
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header bg-success">
                      <h5 class="modal-title text-light">Editar datos personales</h5>
                  </div>
                  <div class="modal-body">
                      <form id="form-edicion">
                          <label for="nombre">Nombre:</label><br>
                          <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" class="form-control"><br>
                          <label for="apellidos">Apellidos:</label><br>
                          <input type="text" id="apellidos" name="apellidos" value="${usuario.apellidos}" class="form-control"><br>
                          <label for="correo">Correo:</label><br>
                          <input type="email" id="correo" name="correo" value="${usuario.correo}" class="form-control"><br>
                          <label for="contraseña">Contraseña actual:</label><br>
                          <input type="password" id="contraseña" name="contraseña" value="" class="form-control"><br>
                          <label for="contraseña">Contraseña nueva:</label><br>
                          <input type="password" id="newcontraseña" name="newcontraseña" value="" class="form-control"><br>
                          <label for="contraseña">Confirmar contraseña:</label><br>
                          <input type="password" id="confirmcontraseña" name="confirmcontraseña" value="" class="form-control"><br>
                              <button type="submit" class="btn btn-success">Guardar Cambios</button>
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
      `;
  
    // Mostrar el modal
    $("#modal-update").modal("show");
  
    // Manejar la envío del formulario
    const form = document.getElementById("form-edicion");
    form.addEventListener("submit", async (event) => {
      event.preventDefault();
      var nombres = $("#nombre").val().trim();
      var apellidos = $("#apellidos").val().trim();
      var email = $("#correo").val().trim();
      var password = $("#contraseña").val().trim();
      var newpassword = $("#newcontraseña").val().trim();
      var confirmcontraseña = $("#confirmcontraseña").val().trim();
      

      if(newpassword != confirmcontraseña){
        Swal.fire({
          title: "Error",
          text: "Las contraseñas no coinciden",
          icon: "error",
        });
        return false;
      }
      else{
        const data = {
          nombre: nombres,
          apellidos: apellidos,
          correo: email,
          password: password,
          rol:"usuario",
          newpassword:  newpassword,
        };
        if (nombres != "" && apellidos != "" && email != ""){
          try {
            const response = await axios.put(
              `${urlServer}/api/usuarios/${usuario.idUsuario}`,
              data
            );
            console.log("Usuario actualizado exitosamente:", response.data);
            Swal.fire({
              title: "Actualización exitosa",
              text: "Usuario actualizado correctamente",
              icon: "success",
            }).then(() => {
                localStorage.clear();
              window.location.href="logout";
          });
          } catch (error) {
            console.error(error);
            if (error.response.status === 404) {
              // Bad Request (dni o email duplicados)
              Swal.fire({
                title: "Error",
                text: error.response.data.message,
                icon: "error",
              });
            } else {
              // Otro tipo de error
              Swal.fire({
                title: "Error",
                text: "Ocurrió un error al procesar la solicitud",
                icon: "error",
              });
            }
          }
        }
  
        else{
          Swal.fire({
            title: "Error",
            text: "Debe ingresar un nombre/apellido/correo",
            icon: "error",
          });
        }
      }
      
    });
  }

})




