package edward.example.com;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import edward.example.com.BaseDatos.DatosOpenHelper;
import edward.example.com.BaseDatos.FeedReaderContract;
import edward.example.com.BaseDatos.FeedReaderContract.FeedEntry;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity {
    private ListView lstDatos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> clientes;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ActMain.this, ActNuevoCliente.class);
                //startActivity(it);
                startActivityForResult(it, 0);

            }
        });

        actualizar();
    }

    private void actualizar() {
        lstDatos = (ListView) findViewById(R.id.lstDatos);
        clientes = new ArrayList<String>();
        String sNombre;
        String sTelefono;

        try {
            datosOpenHelper = new DatosOpenHelper(this);
            conexion = datosOpenHelper.getWritableDatabase();
            String[] projection = {
                    FeedEntry.COLUMN_NAME,
                    FeedEntry.COLUMN_DIREC,
                    FeedEntry.COLUMN_EMAIL,
                    FeedEntry.COLUMN_NUMBER
            };

            Cursor resultado = conexion.query(
                    FeedEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                do {
                    sNombre = resultado.getString(resultado.getColumnIndex(FeedEntry.COLUMN_NAME));
                    sTelefono = resultado.getString(resultado.getColumnIndex(FeedEntry.COLUMN_NUMBER));
                    clientes.add(sNombre + ": " + sTelefono);
                } while (resultado.moveToNext());
                resultado.close();
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lstDatos.setAdapter(adaptador);
        } catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        actualizar();
        //super.onActivityResult(requestCode, resultCode, data);
    }
}

            /*
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM CLIENTE");
            String sNombre;
            String sTelefono;

            Cursor resultado = conexion.rawQuery(sql.toString(), null);

            if (resultado.getCount() > 0){
                resultado.moveToFirst();
                do{
                    sNombre = resultado.getString(resultado.getColumnIndex("NOMBRE"));
                    sTelefono = resultado.getString(resultado.getColumnIndex("TELEFONO"));
                    clientes.add(sNombre + ": " + sTelefono);
                } while (resultado.moveToNext());
            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lstDatos.setAdapter(adaptador);
        } catch (Exception ex){
            AlertDialog.Builder  dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.show();*/






















