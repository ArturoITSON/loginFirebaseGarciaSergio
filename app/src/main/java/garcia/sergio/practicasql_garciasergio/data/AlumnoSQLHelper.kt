package garcia.sergio.practicasql_garciasergio.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import garcia.sergio.practicasql_garciasergio.entities.Alumno

class AlumnoSQLHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "alumnos.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "alumnos"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_FIRST_LASTNAME = "first_last_name"
        private const val COLUMN_SECOND_LASTNAME = "second_last_name"
        private const val COLUMN_EDU_PROGRAM = "educational_program"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableAlumnos = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_FIRST_LASTNAME TEXT, " +
                "$COLUMN_SECOND_LASTNAME TEXT, " +
                "$COLUMN_EDU_PROGRAM TEXT " + ")"

        db?.execSQL(createTableAlumnos)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val dropTableAlumnos = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableAlumnos)

        onCreate(db)

    }


    fun insertAlumno(alumno: Alumno){
        val db = writableDatabase
        val values = ContentValues().apply {

            put(COLUMN_NAME, alumno.nombre)
            put(COLUMN_FIRST_LASTNAME, alumno.apellidoP)
            put(COLUMN_SECOND_LASTNAME, alumno.apellidoM)
            put(COLUMN_EDU_PROGRAM, alumno.carrera)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    //2DO AVANCE PF
    //1ro de Julio
    //Inicio de sesion con firebase Funcional
    //Registro de usuario en firebase Auth
    //Su UI CORREGIDA
    //RAMA NUEVA avance2
    //Opcional: Cambio de contra


}