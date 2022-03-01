package com.example.agenda.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viemCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
        Aluno alunoDevolvido = alunos.get(posicao);
        TextView nomeAluno = viemCriada.findViewById(R.id.item_aluno_nome);
        TextView telefoneAluno = viemCriada.findViewById(R.id.item_aluno_telefone);
        nomeAluno.setText(alunoDevolvido.getNome());
        telefoneAluno.setText(alunoDevolvido.getTelefone());
        return viemCriada;
    }

    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}
