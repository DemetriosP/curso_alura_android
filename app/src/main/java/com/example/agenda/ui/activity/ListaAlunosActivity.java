package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);
        configurarFabNovoAluno();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView alunosLista = findViewById(R.id.activity_lista_alunos_listview);

        alunosLista.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, dao.todos()));
    }

    private void configurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno =
                findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        abrirFormularioAlunoActivity(botaoNovoAluno);
    }

    private void abrirFormularioAlunoActivity(FloatingActionButton botaoNovoAluno) {
        botaoNovoAluno.setOnClickListener(view -> startActivity(new
                Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class)));
    }


}
