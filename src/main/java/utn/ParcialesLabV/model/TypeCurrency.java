package utn.ParcialesLabV.model;

public enum TypeCurrency {
    //tipos de divisas
    EURO("euro", 92.92),
    DOLAR("dolar", 112.02);

    private String tipoDivisa;
    private  Double valor;
    TypeCurrency(String tipoDivisa, Double valor)
    {
        this.tipoDivisa=tipoDivisa;
        this.valor=valor;
    }

    public static TypeCurrency findCurrency(final String value) {
        for (TypeCurrency c : values()) {
            if (c.toString().equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.format("Tipo de divisa no valida: %s", value));
    }


    public String getTipoDivisa() {
        return tipoDivisa;
    }
    public Double getValor() {
        return valor;
    }
}
