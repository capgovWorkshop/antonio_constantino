package com.example.pesc.hello;

/**
 * Created by PESC on 28/05/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageStats;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class PessoaDAO {

    private SQLiteDatabase db;
    private Context context;

    public PessoaDAO(Context context) {
        DBHelper dbh = new DBHelper(context);
        this.db = dbh.getDb();
        this.context = context;
    }

    public List<Pessoa> getListarCadastro() {

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        String[] columns = {Pessoa.NOME, Pessoa.EMAIL, Pessoa.SENHA};
        String selection = "";
        String selectionArgs [] = {};
        String groupBy = "";
        String having = "";
        String orderBy = "";
        String limit = "";

        Cursor cursor =
                db.query(Pessoa.TABLE, // a. table
                        columns, // b. column names
                        selection, // c. selections
                        selectionArgs, // d. selections args
                        groupBy, // e. group by
                        having, // f. having
                        orderBy, // g. order by
                        limit); // h. limit

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(cursor.getString(0));
            pessoa.setSenha(cursor.getString(1));
            pessoa.setEmail(cursor.getString(2));

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateTime = new Date();
            try {
                dateTime = format.parse(cursor.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            pessoas.add(pessoa);

            cursor.moveToNext();
        }

        cursor.close();
        return pessoas;
    }

    public List<Pessoa> getListarCadastro2() {

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        String selectQuery = "SELECT " +
                Pessoa.NOME + ", " +
                Pessoa.EMAIL + ", " +
                Pessoa.SENHA + ", " +
                 " FROM ?";

        String params[] = {Pessoa.TABLE};

        Cursor cursor = db.rawQuery(selectQuery, params);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            Pessoa pess = new Pessoa();
            pess.setNome(cursor.getString(0));
            pess.setSenha(cursor.getString(1));
            pess.setEmail(cursor.getString(2));

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateTime = new Date();
            try {
                dateTime = format.parse(cursor.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }



            pessoas.add(pess);

            cursor.moveToNext();
        }

        cursor.close();
        return pessoas;
    }

    public Pessoa login(String email, String senha) {

        String[] columns = {Pessoa.NOME, Pessoa.EMAIL, Pessoa.SENHA};
        String selection = Pessoa.EMAIL + " LIKE  ? AND " + Pessoa.SENHA + " LIKE ?";
        String selectionArgs [] = {email, senha};
        String groupBy = "";
        String having = "";
        String orderBy = "";
        String limit = "";

        Cursor cursor =
                db.query(Pessoa.TABLE, // a. table
                        columns, // b. column names
                        selection, // c. selections
                        selectionArgs, // d. selections args
                        groupBy, // e. group by
                        having, // f. having
                        orderBy, // g. order by
                        limit); // h. limit

        Pessoa pessoa = null;

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            pessoa = new Pessoa();
            pessoa.setNome(cursor.getString(0));
            pessoa.setSenha(cursor.getString(1));
            pessoa.setEmail(cursor.getString(2));

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateTime = new Date();
            try {
                dateTime = format.parse(cursor.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            cursor.moveToNext();
        }

        cursor.close();
        return pessoa;
    }

    public Pessoa login2(String email, String senha) {

        String selectQuery = "SELECT " +
                Pessoa.NOME + ", " +
                Pessoa.EMAIL + ", " +
                Pessoa.SENHA + ", " +
                 " FROM ? "
                + "Where " + Pessoa.EMAIL + " LIKE  ? AND " + Pessoa.SENHA + " LIKE ?";

        String params[] = {email, senha};

        Cursor cursor = db.rawQuery(selectQuery, params);

        Pessoa pessoa = null;

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            pessoa = new Pessoa();
            pessoa.setNome(cursor.getString(0));
            pessoa.setSenha(cursor.getString(1));
            pessoa.setEmail(cursor.getString(2));

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateTime = new Date();
            try {
                dateTime = format.parse(cursor.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            cursor.moveToNext();
        }

        cursor.close();
        return pessoa;
    }

    public boolean atualizarDados(Pessoa pessoa) {

        try {

            String tabela = Pessoa.TABLE;
            String whereClause = "email = ?";
            String[] whereArgs = new String[]{ pessoa.getEmail() };
            ContentValues values = new ContentValues(3);
            values.put(Pessoa.NOME, pessoa.getNome());
            values.put(Pessoa.SENHA, pessoa.getSenha());
            values.put(Pessoa.EMAIL, pessoa.getEmail());


            db.update(tabela, values, whereClause, whereArgs);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean atualizarDados2(Pessoa pessoa) {

        try {

            String[] args = { pessoa.getSenha(), pessoa.getEmail() };
            String query =
                    "UPDATE " + Pessoa.TABLE
                            + " SET "   + Pessoa.SENHA + "= ?"
                            + " WHERE " + Pessoa.EMAIL +" Like ?";
            Cursor cursor = db.rawQuery(query, args);
            cursor.moveToFirst();
            cursor.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean inserirPessoa(Pessoa pessoa) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


            String tabela = Pessoa.TABLE;
            ContentValues values = new ContentValues(6);

            values.put(Pessoa.NOME, pessoa.getNome());
            values.put(Pessoa.SENHA, pessoa.getSenha());
            values.put(Pessoa.EMAIL, pessoa.getEmail());

            int id = (int) db.insertOrThrow(tabela, null, values);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean inserirPessoa2(Pessoa pessoa) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            String[] args = { pessoa.getNome(), pessoa.getEmail(), pessoa.getSenha()};
            String query =
                    "INSERT ("
                            + Pessoa.NOME + ", "
                            + Pessoa.EMAIL + ", "
                            + Pessoa.SENHA + ", "

                            + ") INTO " + Pessoa.TABLE
                            +" VALUES(?, ?, ?, ?, ?, ?)";
            Cursor cursor = db.rawQuery(query, args);
            cursor.moveToFirst();
            cursor.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
