//1

const duracionEmision = (inicio, fin) => {
    if (((0 > Number(inicio))||(Number(inicio)>1439)) || ((0 > Number(fin))||(Number(fin)>1439))) {
        return 1
    } else {
         if (Number(inicio) == Number(fin)) {
            return 0;
        }else if (Number(inicio) > Number(fin)) {
            const hora = 1440 - Number(inicio);
            return hora + Number(fin);
        } else{
            return ((Number(fin) - Number(inicio)));
        } 
        
    }
};

console.log(duracionEmision(600, 640));



//2

const cortes = [
    {
        archivo: "cabecera.wav", entrada: 0, salida: 12,
        descartado: false
    },
    {
        archivo: "entrevista.wav", entrada: 20, salida: 95,
        descartado: false
    },
    {
        archivo: "prueba.wav", entrada: 0, salida: 40,
        descartado: true
    },
    {
        archivo: "ambiente.wav", entrada: 5, salida: 23,
        descartado: false
    }
];

const prepararMontaje = cortes => {
    if ((cortes.every(c => c.descartado == false)) == true) {
        return []
    } else {
        return cortes
            .filter(corte => corte.descartado == false)
            .map(corte => corte.archivo)
    }
};
console.log(prepararMontaje(cortes));


//3

const reservas = [
 { estudio: "A", inicio: 600, fin: 660, confirmada: true },
 { estudio: "B", inicio: 620, fin: 700, confirmada: true },
 { estudio: "A", inicio: 660, fin: 690, confirmada: false },
 { estudio: "A", inicio: 720, fin: 780, confirmada: true }
];

const hayConflicto = (reservas, estudio,inicio) =>{
    return reservas
        .filter(r => r.estudio == estudio)
        .some(r => r.inicio == inicio)
};
console.log(hayConflicto(reservas, "A",600));










