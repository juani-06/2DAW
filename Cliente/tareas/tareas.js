const tareas = [
    { id: 1, titulo: "Estudiar JavaScript", completada: true, prioridad: 2 },
    { id: 2, titulo: "Practicar arrays", completada: false, prioridad: 1 },
    { id: 3, titulo: "Repasar objetos", completada: false, prioridad: 3 }
];


// a) Obtener tareas pendientes
function obtenerPendientes(tareas) {
    return tareas.filter(tarea => !tarea.completada);
}


// b) Buscar una tarea por ID
function buscarTarea(tareas, id) {
    return tareas.find(tarea => tarea.id === id);
}


// c) Completar una tarea
function completarTarea(tareas, id) {
    return tareas.map(tarea => {
        if (tarea.id === id) {
            return {
                ...tarea,
                completada: true
            };
        }

        return tarea;
    });
}


// d) Añadir una tarea
function añadirTarea(tareas, nuevaTarea) {
    return [...tareas, nuevaTarea];
}


// e) Calcular estadísticas
function calcularEstadisticas(tareas) {
    const total = tareas.length;

    const completadas = tareas.reduce((contador, tarea) => {
        return contador + (tarea.completada ? 1 : 0);
    }, 0);

    const pendientes = total - completadas;

    return {
        total,
        completadas,
        pendientes
    };
}


// PRUEBAS

console.log(obtenerPendientes(tareas));

console.log(buscarTarea(tareas, 2));

const nuevasTareas = completarTarea(tareas, 2);
console.log(nuevasTareas);

console.log(tareas); // El original sigue igual


const nueva = {
    id: 4,
    titulo: "Practicar reduce",
    completada: false,
    prioridad: 2
};

const tareasConNueva = añadirTarea(tareas, nueva);
console.log(tareasConNueva);

console.log(calcularEstadisticas(tareas));