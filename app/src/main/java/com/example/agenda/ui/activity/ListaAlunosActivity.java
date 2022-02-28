package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configurarFabNovoAluno();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView alunosLista = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(alunosLista);
        configuraListenerCliqueItem(alunosLista);
        configuraListenerCliqueLongoItem(alunosLista);
    }

    private void configuraListenerCliqueLongoItem(ListView alunosLista) {
        alunosLista.setOnItemLongClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            removeAluno(alunoEscolhido);
            return true;
        });
    }

    private void removeAluno(Aluno aluno) {
        dao.removerAluno(aluno);
        adapter.remove(aluno);
    }

    private void configuraListenerCliqueItem(ListView alunosLista) {
        alunosLista.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent goToFormActivity = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        goToFormActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(goToFormActivity);
    }

    private void configuraAdapter(ListView alunosLista) {
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1);
        alunosLista.setAdapter(adapter);
    }

    private void configurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno =
                findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        abrirFormularioModoInsereAluno(botaoNovoAluno);
    }

    private void abrirFormularioModoInsereAluno(FloatingActionButton botaoNovoAluno) {
        botaoNovoAluno.setOnClickListener(view -> startActivity(new
                Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class)));
    }


}
