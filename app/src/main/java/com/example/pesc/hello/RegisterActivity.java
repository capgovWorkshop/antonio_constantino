package com.example.pesc.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button button = (Button) findViewById(R.id.confirmar);

        final EditText nomeView = (EditText) findViewById(R.id.editTextNome);
        final EditText senhaView = (EditText) findViewById(R.id.editTextSenha);
        final EditText emailView = (EditText) findViewById(R.id.editTextEmail);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Pessoa pessoa = new Pessoa(nomeView.getText().toString(), emailView.getText().toString(), senhaView.getText().toString());

                PessoaDAO p = new PessoaDAO(RegisterActivity.this);
                p.inserirPessoa(pessoa);
                SharedPreferences mPrefs = getSharedPreferences("pessoa", MODE_PRIVATE);

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("nome", pessoa.getNome());
                prefsEditor.putString("email", pessoa.getEmail());
                prefsEditor.putString("senha", pessoa.getSenha());
                prefsEditor.commit();




                /*Gson gson = new Gson()*/


                Intent intent = new Intent(RegisterActivity.this, ResultActivity.class);

                intent.putExtra("pessoa", pessoa);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
