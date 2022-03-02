package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();
    }

    private void criaAlunoTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
        dao.salvar(new Aluno("Fran", "1122223333", "fran@gmail.com"));
    }
}
