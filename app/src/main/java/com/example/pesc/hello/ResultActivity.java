package com.example.pesc.hello;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences sharedpreferences = getSharedPreferences("pessoa", this.MODE_PRIVATE);
        String nome= sharedpreferences.getString("nome", null);

        Pessoa pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");

        TextView nomeView = (TextView) findViewById(R.id.nomeView);
        TextView emailView = (TextView) findViewById(R.id.emailView);
        TextView senhaView = (TextView) findViewById(R.id.senhaView);

        nomeView.setText(nome);
        emailView.setText(pessoa.getEmail());
        senhaView.setText(pessoa.getSenha());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
