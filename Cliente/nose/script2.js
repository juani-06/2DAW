let productos = [
    { nombre: "Teclado", precio: 35, stock: 4 },
    { nombre: "Ratón", precio: 20, stock: 0 },
    { nombre: "Monitor", precio: 180, stock: 3 },
    { nombre: "Auriculares", precio: 45, stock: 8 }
];


for (const producto of productos) {
    console.log(`${producto.nombre} - ${producto.precio}€ - Stock: ${producto.stock}`)
}
