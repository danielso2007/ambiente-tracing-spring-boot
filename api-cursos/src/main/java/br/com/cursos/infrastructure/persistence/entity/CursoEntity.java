package br.com.cursos.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import br.com.cursos.application.domain.model.Curso;

@Entity
@Table(name = "CURSO")
@SuppressWarnings({"PMD.DataClass"})
public class CursoEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2891802628474642528L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String numeroMatricula;

    @Column(nullable = false, unique = true, length = 10)
    private String numeroCurso;

    @Column(nullable = false, length = 120)
    private String nomeCurso;

    @Column(nullable = false, length = 120)
    private String categoriaCurso;

    @Column(nullable = false, length = 120)
    private String preRequisito;

    @Column(nullable = false, length = 120)
    private String nomeProfessor;

    @Column(nullable = false, length = 30)
    private String periodoCurso;

    @Column(nullable = false)
    private LocalDateTime dataInscricao;

    public CursoEntity() {
    }

    public CursoEntity(UUID id, String numeroMatricula, String numeroCurso, String nomeCurso, String categoriaCurso,
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

    public CursoEntity fromDomain(Curso domain) {
        return new CursoEntity(domain.getId(), domain.getNumeroMatricula(), domain.getNumeroCurso(), domain.getNomeCurso(),
            domain.getCategoriaCurso(), domain.getPreRequisito(), domain.getNomeProfessor(), domain.getPeriodoCurso(),
            domain.getDataInscricao());
    }

    public Curso toDomain() {
        return new Curso(id, numeroMatricula, numeroCurso, nomeCurso, categoriaCurso,
        preRequisito, nomeProfessor, periodoCurso, dataInscricao);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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
