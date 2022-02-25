package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

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
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(alunosLista, alunos);
        configuraListenerCliqueItem(alunosLista);
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

    private void configuraAdapter(ListView alunosLista, List<Aluno> alunos) {
        alunosLista.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, alunos));
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
