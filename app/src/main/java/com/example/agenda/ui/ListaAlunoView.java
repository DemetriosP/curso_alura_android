package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunoView {

    private final Context contexto;
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;

    public ListaAlunoView(Context contexto) {
        this.contexto = contexto;
        this.adapter = new ListaAlunosAdapter(this.contexto);
        dao = new AlunoDAO();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(contexto)
                .setTitle("Remover aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    removeAluno(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void removeAluno(Aluno aluno) {
        dao.removerAluno(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView alunosLista) {
        alunosLista.setAdapter(adapter);

    }

}
