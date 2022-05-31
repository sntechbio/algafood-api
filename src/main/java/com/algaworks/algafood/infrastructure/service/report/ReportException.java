package com.algaworks.algafood.infrastructure.service.report;

public class ReportException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReportException(String message, Throwable couse) {
        super(message, couse);
    }

    public ReportException(String message) {
        super(message);
    }
}
