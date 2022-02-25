package com.example.agenda.dao;

import androidx.annotation.Nullable;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorId = 1;

    public void salvar(Aluno alunoCriado) {
        alunoCriado.setId(contadorId);
        alunos.add(alunoCriado);
        atualizaId();
    }

    private void atualizaId() {
        contadorId++;
    }

    public void edit(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) if (a.getId() == aluno.getId()) return a;
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
