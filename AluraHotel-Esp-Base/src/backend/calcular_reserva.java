package backend;

import java.text.NumberFormat;

public class calcular_reserva {
	public String calcularCosto(String fechaEntrada, String fechaSalida) {
		
        fechaEntrada = fechaEntrada.replace("-", "");
        fechaSalida = fechaSalida.replace("-", "");
        int EnNum = Integer.valueOf(fechaEntrada);
        int SaNum = Integer.valueOf(fechaSalida);
        
        int total = SaNum - EnNum;
        total = total * 60000;
        
        NumberFormat fNumberFormat = NumberFormat.getCurrencyInstance();
		String resultado = fNumberFormat.format(total);
		
		return resultado;
	}
}
