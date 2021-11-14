package br.gov.sp.fatec.nemo.usecases.impls.dtos;

public class DefaultError {
    private int code;
    private String message;

    public DefaultError(int value, String erro) {
        this.code = value;
        this.message = erro;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
