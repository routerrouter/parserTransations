package com.bycoders.parsertransations;

import com.bycoders.parsertransations.repository.TransacaoRepository;
import com.bycoders.parsertransations.service.ParserTransationServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@DisplayName("TransationServiceTest")
public class TransationServiceTest extends ParserTransationsApplicationTests {

    @MockBean
    private TransacaoRepository repository;

    @Autowired
    private ParserTransationServiceImpl transationService;


    @Test
    @DisplayName("testar tipo de ficheiro (.txt)")
    public void tipoFile(String tipo) {

    }

    /*@Before
    public String setup() {
        String tipo = ".txt";
        return tipo;
    }*/


}
