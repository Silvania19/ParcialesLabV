package utn.ParcialesLabV.model;

public enum TypePerson {
    REPRESENTANTE("representante"),
    JUGADOR("jugador"),
    AMIGO("amigo");

    private  String typePerson;
    TypePerson(String typePerson)
    {
        this.typePerson=typePerson;
    }
    public static TypePerson findCurrency(final String value) {
        for (TypePerson p : values()) {
            if (p.toString().equals(value)) {
                return p;
            }
        }
        throw new IllegalArgumentException(String.format("Tipo de divisa no valida: %s", value));
    }
    public String getTypePerson()
    {
        return typePerson;
    }
}
