package com.example.library.DTO;
import java.time.LocalDate;
public class AutorCreateRequest {
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String narodowosc;
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getNarodowosc() {
        return narodowosc;
    }
}
