package br.com.cursos.interfaces.handlers;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@SuppressWarnings({"PMD.DataClass"})
public class ProblemDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -7954804742252712002L;

    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
    private Instant timestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
