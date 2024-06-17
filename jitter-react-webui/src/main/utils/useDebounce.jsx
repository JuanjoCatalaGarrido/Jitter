import { useState, useEffect } from "react";

function useDebounce(value, delay) {
    const [debouncedValue, setDebouncedValue] = useState(value);

    useEffect(() => {
        // Crea un temporizador que actualizará el valor "debouncedValue" después del retraso especificado
        const handler = setTimeout(() => {
            setDebouncedValue(value);
        }, delay);

        // Limpia el temporizador anterior si el efecto se ejecuta nuevamente antes de que el tiempo se complete
        return () => {
            clearTimeout(handler);
        };
    }, [value, delay]); // Este efecto se ejecuta cada vez que cambian "value" o "delay"

    return debouncedValue;
}

export default useDebounce;
