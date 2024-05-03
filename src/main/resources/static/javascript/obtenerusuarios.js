// URL de tu servidor Spring Boot
const baseUrl = 'http://localhost:8090';

// Función para obtener la lista de usuarios
async function obtenerUsuarios() {
    try {
        // Realizar una solicitud GET a la ruta /api/usuarios
        const response = await axios.get(`${baseUrl}/api/usuarios`);
        
        // Devolver los datos de la respuesta
        return response.data;
    } catch (error) {
        // Manejar errores si la solicitud falla
        console.error('Error al obtener la lista de usuarios:', error);
        return null;
    }
}

// Función para llenar la tabla de usuarios
async function llenarTablaUsuarios() {
    // Obtener la tabla donde se mostrarán los usuarios
    const tabla = document.getElementById('tabla-usuarios');

    // Obtener la lista de usuarios
    const usuarios = await obtenerUsuarios();

    // Verificar si se obtuvieron usuarios correctamente
    if (usuarios) {
        // Limpiar el contenido existente de la tabla
        tabla.getElementsByTagName('tbody')[0].innerHTML = '';

        // Iterar sobre la lista de usuarios y agregar filas a la tabla
        usuarios.forEach(usuario => {
            const fila = `
                <tr>
                    <td>${usuario.idUsuario}</td>
                    <td>${usuario.dni}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.apellidos}</td>
                    <td>${usuario.correo}</td>
                    <td>${usuario.rol}</td>
                </tr>`;
            tabla.getElementsByTagName('tbody')[0].innerHTML += fila;
        });
    }
}

// Llenar la tabla de usuarios cuando la página se carga
document.addEventListener('DOMContentLoaded', llenarTablaUsuarios);
