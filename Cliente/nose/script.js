let productos = [
    { nombre: "Teclado", precio: 35, stock: 4 },
    { nombre: "Ratón", precio: 20, stock: 0 },
    { nombre: "Monitor", precio: 180, stock: 3 },
    { nombre: "Auriculares", precio: 45, stock: 8 }
];

function estaDisponible(producto) {
    if (producto.stock > 0) {
        console.log("True");
    } else {
        console.log("False");
    }
}

for (let i in productos) {
    estaDisponible(productos[i]);
}
