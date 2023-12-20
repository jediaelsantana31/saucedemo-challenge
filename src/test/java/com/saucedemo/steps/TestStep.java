package com.saucedemo.steps;

import com.saucedemo.actionwords.Utils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class TestStep {
    @Dado("possa acessar a tela de login do sistema")
    public void possa_acessar_a_tela_de_login_do_sistema() {
      new Utils().openApplication();
    }

    @Quando("informar um {string} válido")
    public void informar_um_válido(String string) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }

    @Quando("inserir uma {string} válida")
    public void inserir_uma_válida(String string) {
        // Write code here that turns the phrase above into concrete actions
        //  throw new io.cucumber.java.PendingException();
    }

    @Quando("clicar no botão login")
    public void clicar_no_botão_login() {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }

    @Então("o sistema deve exibir os produtos")
    public void o_sistema_deve_exibir_os_produtos() {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }
}
