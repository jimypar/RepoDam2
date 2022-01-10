package base.enums;

/**
 * Esta clase enum enumera las constantes con las que se rellena
 * el JComoboBox comboGenero de la vista.
 * Representan los géneros literarios que existen.
 */
public enum Marcas {
    ALFAROMEO("Alfa Romeo"),
    ASTONMARTIN("Aston Martin"),
    AUDI("Audi"),
    BENTLEY("Bentley"),
    BMW("BMW"),
    BUGATTI("Bugatti"),
    CADILLAC("Cadillac"),
    CHEVROLET("Chevrolet"),
    CITROEN("Citroën"),
    CUPRA("Cupra"),
    DACIA("Dacia"),
    DS("DS"),
    FERRARI("Ferrari"),
    FIAT("Fiat"),
    FORD("Ford"),
    HONDA("Honda"),
    HYUNDAI("Hyundai"),
    JAGUAR("Jaguar"),
    JEEP("Jeep"),
    KIA("KIA"),
    LAMBO("Lamborghini"),
    LANCIA("Lancia"),
    LANDROVER("Land Rover"),
    MASERATI("Maserati"),
    MAZDA("Mazda"),
    MCLAREN("McLaren"),
    MERCEDES("Mercedes"),
    MINI("Mini"),
    MITSUBISHI("Mitsubishi"),
    NISSAN("Nissan"),
    OPEL("Opel"),
    PEUGEOT("Peugeot"),
    PORSCHE("Porsche"),
    RENAULT("Renault"),
    ROLLSROYCE("Rolls-Royce"),
    SEAT("Seat"),
    SKODA("Škoda"),
    SUBARU("Subaru"),
    SUZUKI("Suzuki"),
    TESLA("Tesla"),
    TOYOTA("Toyota"),
    VOLKSWAGEN("Volkswagen"),
    VOLVO("Volvo");

    private String valor;

    Marcas(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
