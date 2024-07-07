// Llenar la tabla de usuarios cuando la página se carga
$(document).ready(function () {
  llenarTablaUsuarios();
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

// Función para manejar borrar un usuario
async function borrarUsuario(idUsuario) {
    // Obtener los detalles del usuario por su ID
    const usuario = await obtenerUsuarioPorId(idUsuario);
  
    // Verificar si se obtuvieron los detalles del usuario correctamente
    if (usuario) {
      try {
        const response = await axios.delete(
          `http://ec2-13-59-233-23.us-east-2.compute.amazonaws.com:8090/api/usuarios/${usuario.idUsuario}`
        );
        Swal.fire({
          title: "Eliminación exitosa",
          text: "Usuario eliminado correctamente",
          icon: "success",
        }).then(() => {
          location.reload();
      });
      } catch (error) {
        console.error(error);
        if (error.response.status === 404) {
          Swal.fire({
            title: "Error",
            text: error.response.data.message,
            icon: "error",
          });
        } else {
          Swal.fire({
            title: "Error",
            text: "Ocurrió un error al procesar la solicitud",
            icon: "error",
          });
        }
      }
        
    }
  }
  

// Función para llenar la tabla de usuarios con DataTables
async function llenarTablaUsuarios() {
  // Obtener la tabla donde se mostrarán los usuarios
  const tabla = $("#myTable").DataTable({
    language: {
      url: "//cdn.datatables.net/plug-ins/2.0.6/i18n/es-MX.json",
    },
    responsive: true, // Habilitar la funcionalidad responsive
  });

  // Obtener la lista de usuarios
  const usuarios = await obtenerUsuarios();

  // Verificar si se obtuvieron usuarios correctamente
  if (usuarios) {
    // Limpiar el contenido existente de la tabla
    tabla.clear().draw();

    // Iterar sobre la lista de usuarios y agregar filas a la tabla
    usuarios.forEach((usuario) => {
      tabla.row
        .add([
          usuario.idUsuario,
          usuario.dni,
          usuario.nombre,
          usuario.apellidos,
          usuario.correo,
          usuario.rol,
          `<div>  <button onclick="editarUsuario(${usuario.idUsuario})" class="btn btn-success">Editar</button>
                <button onclick="borrarUsuario(${usuario.idUsuario})" class="btn btn-danger">Eliminar</button></div>`,
        ])
        .draw();
    });
  }
}

// URL de tu servidor Spring Boot
const baseUrl = "http://ec2-13-59-233-23.us-east-2.compute.amazonaws.com:8090";

// Función para obtener la lista de usuarios
async function obtenerUsuarios() {
  try {
    // Realizar una solicitud GET a la ruta /api/usuarios
    const response = await axios.get(`${baseUrl}/api/usuarios`);

    // Devolver los datos de la respuesta
    return response.data;
  } catch (error) {
    // Manejar errores si la solicitud falla
    console.error("Error al obtener la lista de usuarios:", error);
    return null;
  }
}

// Función para obtener los detalles de un usuario por su ID
async function obtenerUsuarioPorId(id) {
  try {
    // Realizar una solicitud GET a la ruta /api/usuarios/{id}
    const response = await axios.get(`${baseUrl}/api/usuarios/${id}`);

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
  const modal = document.getElementById("modal-edicion");

  // Llenar el modal con los detalles del usuario
  modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-success">
                    <h5 class="modal-title text-light">Editar Usuario</h5>
                </div>
                <div class="modal-body">
                    <form id="form-edicion">
                        <label for="nombre">Nombre:</label><br>
                        <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" class="form-control"><br>
                        <label for="apellidos">Apellidos:</label><br>
                        <input type="text" id="apellidos" name="apellidos" value="${usuario.apellidos}" class="form-control"><br>
                        <label for="correo">Correo:</label><br>
                        <input type="email" id="correo" name="correo" value="${usuario.correo}" class="form-control"><br>
                        <label for="contraseña">Contraseña:</label><br>
                        <input type="password" id="contraseña" name="contraseña" value="${usuario.password}" class="form-control"><br>
                        <label for="rol">Rol:</label><br>
                        <input type="text" id="rol" name="rol" value="${usuario.rol}" class="form-control"><br><br>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-success">Guardar Cambios</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    `;

  // Mostrar el modal
  $("#modal-edicion").modal("show");

  // Manejar la envío del formulario
  const form = document.getElementById("form-edicion");
  form.addEventListener("submit", async (event) => {
    event.preventDefault();
    var nombres = $("#nombre").val().trim();
    var apellidos = $("#apellidos").val().trim();
    var email = $("#correo").val().trim();
    var password = $("#contraseña").val().trim();
    var rol = $("#rol").val().trim();

    const data = {
      nombre: nombres,
      apellidos: apellidos,
      correo: email,
      password: password,
      rol: rol,
    };
    try {
      const response = await axios.put(
        `http://ec2-13-59-233-23.us-east-2.compute.amazonaws.com:8090/api/usuario/${usuario.idUsuario}`,
        data
      );
      console.log("Usuario actualizado exitosamente:", response.data);
      Swal.fire({
        title: "Actualización exitosa",
        text: "Usuario actualizado correctamente",
        icon: "success",
      }).then(() => {
        location.reload();
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
  });
}
