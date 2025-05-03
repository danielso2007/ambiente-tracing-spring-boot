package br.com.cursos.application.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings({"PMD.DataClass"})
public class Curso implements Serializable {

    @Serial
    private static final long serialVersionUID = 1973505386818798535L;

    private UUID id;
    private String numeroMatricula;
    private String numeroCurso;
    private String nomeCurso;
    private String categoriaCurso;
    private String preRequisito;
    private String nomeProfessor;
    private String periodoCurso;
    private LocalDateTime dataInscricao;

    public Curso() {
    }

    public Curso(UUID id, String numeroMatricula, String numeroCurso, String nomeCurso, String categoriaCurso,
                      String preRequisito, String nomeProfessor, String periodoCurso, LocalDateTime dataInscricao) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.numeroCurso = numeroCurso;
        this.nomeCurso = nomeCurso;
        this.categoriaCurso = categoriaCurso;
        this.preRequisito = preRequisito;
        this.nomeProfessor = nomeProfessor;
        this.periodoCurso = periodoCurso;
        this.dataInscricao = dataInscricao;
    }

    public String getPeriodoCurso() {
        return periodoCurso;
    }

    public void setPeriodoCurso(String periodoCurso) {
        this.periodoCurso = periodoCurso;
    }

    public UUID getId() {    
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNumeroCurso() {
        return numeroCurso;
    }

    public void setNumeroCurso(String numeroCurso) {
        this.numeroCurso = numeroCurso;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getCategoriaCurso() {
        return categoriaCurso;
    }

    public void setCategoriaCurso(String categoriaCurso) {
        this.categoriaCurso = categoriaCurso;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public void setPreRequisito(String preRequisito) {
        this.preRequisito = preRequisito;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }
}
