package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

public class TextValue {
    private String text;
    private Long value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
