package com.sembugs.aquariusfinder.modelos;

public class Morador {
    private int iMorador;
    private int iUnidade;
    private String sNome;
    private String sCpf;
    private String sEmail;
    private String dDatanasc;
    private String sTagbio;
    private String sTagcartao;
    private String sTel1;
    private String sTel2;
    private Boolean bAtivo;
    private Boolean bInquiino;
    private String sObs;
    private Byte byteFoto;

    public Morador() {

    }

    public int getiMorador() {
        return iMorador;
    }

    public void setiMorador(int iMorador) {
        this.iMorador = iMorador;
    }

    public int getiUnidade() {
        return iUnidade;
    }

    public void setiUnidade(int iUnidade) {
        this.iUnidade = iUnidade;
    }

    public String getsNome() {
        return sNome;
    }

    public void setsNome(String sNome) {
        this.sNome = sNome;
    }

    public String getsCpf() {
        return sCpf;
    }

    public void setsCpf(String sCpf) {
        this.sCpf = sCpf;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getdDatanasc() {
        return dDatanasc;
    }

    public void setdDatanasc(String dDatanasc) {
        this.dDatanasc = dDatanasc;
    }

    public String getsTagbio() {
        return sTagbio;
    }

    public void setsTagbio(String sTagbio) {
        this.sTagbio = sTagbio;
    }

    public String getsTagcartao() {
        return sTagcartao;
    }

    public void setsTagcartao(String sTagcartao) {
        this.sTagcartao = sTagcartao;
    }

    public String getsTel1() {
        return sTel1;
    }

    public void setsTel1(String sTel1) {
        this.sTel1 = sTel1;
    }

    public String getsTel2() {
        return sTel2;
    }

    public void setsTel2(String sTel2) {
        this.sTel2 = sTel2;
    }

    public Boolean getbAtivo() {
        return bAtivo;
    }

    public void setbAtivo(Boolean bAtivo) {
        this.bAtivo = bAtivo;
    }

    public Boolean getbInquiino() {
        return bInquiino;
    }

    public void setbInquiino(Boolean bInquiino) {
        this.bInquiino = bInquiino;
    }

    public String getsObs() {
        return sObs;
    }

    public void setsObs(String sObs) {
        this.sObs = sObs;
    }

    public Byte getByteFoto() {
        return byteFoto;
    }

    public void setByteFoto(Byte byteFoto) {
        this.byteFoto = byteFoto;
    }

    @Override
    public String toString() {
        return sNome;
    }
}

