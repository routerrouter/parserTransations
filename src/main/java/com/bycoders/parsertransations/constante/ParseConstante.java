package com.bycoders.parsertransations.constante;

public abstract class ParseConstante {
    private static final int TIPO_INDEX_INICIO = 1;
    private static final int TIPO_INDEX_FIM = 1;

    private static final int DATA_INDEX_INICIO = 2;
    private static final int DATA_INDEX_FIM = 9;

    private static final int VALOR_INDEX_INICIO = 10;
    private static final int VALOR_INDEX_FIM = 19;

    private static final int CPF_INDEX_INICIO = 20;
    private static final int CPF_INDEX_FIM = 30;

    private static final int CARTAO_INDEX_INICIO = 31;
    private static final int CARTAO_INDEX_FIM = 42;

    private static final int HORA_INDEX_INICIO = 43;
    private static final int HORA_INDEX_FIM = 48;

    private static final int DONO_LOJA_INDEX_INICIO = 49;
    private static final int DONO_LOJA_INDEX_FIM = 62;

    private static final int NOME_LOJA_INDEX_INICIO = 63;
    private static final int NOME_LOJA_INDEX_FIM = 80;

    public static int getTipoIndexInicio() {
        return TIPO_INDEX_INICIO;
    }

    public static int getTipoIndexFim() {
        return TIPO_INDEX_FIM;
    }

    public static int getDataIndexInicio() {
        return DATA_INDEX_INICIO;
    }

    public static int getDataIndexFim() {
        return DATA_INDEX_FIM;
    }

    public static int getValorIndexInicio() {
        return VALOR_INDEX_INICIO;
    }

    public static int getValorIndexFim() {
        return VALOR_INDEX_FIM;
    }

    public static int getCpfIndexInicio() {
        return CPF_INDEX_INICIO;
    }

    public static int getCpfIndexFim() {
        return CPF_INDEX_FIM;
    }

    public static int getCartaoIndexInicio() {
        return CARTAO_INDEX_INICIO;
    }

    public static int getCartaoIndexFim() {
        return CARTAO_INDEX_FIM;
    }

    public static int getHoraIndexInicio() {
        return HORA_INDEX_INICIO;
    }

    public static int getHoraIndexFim() {
        return HORA_INDEX_FIM;
    }

    public static int getDonoLojaIndexInicio() {
        return DONO_LOJA_INDEX_INICIO;
    }

    public static int getDonoLojaIndexFim() {
        return DONO_LOJA_INDEX_FIM;
    }

    public static int getNomeLojaIndexInicio() {
        return NOME_LOJA_INDEX_INICIO;
    }

    public static int getNomeLojaIndexFim() {
        return NOME_LOJA_INDEX_FIM;
    }
}
