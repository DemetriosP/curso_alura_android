package com.example.agenda.dao;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno alunoCriado) { alunos.add(alunoCriado); }

    public List<Aluno> todos() { return new ArrayList<>(alunos); }
}
