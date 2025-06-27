package garcia.sergio.practicasql_garciasergio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import garcia.sergio.practicasql_garciasergio.data.AlumnoSQLHelper
import garcia.sergio.practicasql_garciasergio.entities.Alumno

class MainActivity : AppCompatActivity() {

    private lateinit var db: AlumnoSQLHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        db = AlumnoSQLHelper(this)



        val nombre : EditText = findViewById(R.id.nombre)
        val apellidoP : EditText = findViewById(R.id.apellido_paterno)
        val apellidoM : EditText = findViewById(R.id.apellido_materno)
        val carrera : EditText = findViewById(R.id.carrera)
        val btnguardar : Button = findViewById(R.id.btn_guardar)

        btnguardar.setOnClickListener{


            var nombreGuardado : String = nombre.text.toString()
            var apellidoPGuardado : String = apellidoP.text.toString()
            var apellidoMGuardado : String = apellidoM.text.toString()
            var carreraGuardada : String = carrera.text.toString()


            val alumno = Alumno(nombreGuardado, apellidoPGuardado, apellidoMGuardado, carreraGuardada)

            db.insertAlumno(alumno)

            Toast.makeText(this,"Alumno guardado", Toast.LENGTH_SHORT).show()



        }


    }


}