package garcia.sergio.practicaautenticaciongarciasergio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth
        val email: EditText = findViewById(R.id.etrEmail)
        val password : EditText = findViewById(R.id.etrPassword)
        val confirmPassword : EditText = findViewById(R.id.etrConfirmPassword)
        val errorTv : TextView = findViewById(R.id.tvrError)


        errorTv.visibility = View.INVISIBLE


        findViewById<Button>(R.id.btnRegister).setOnClickListener{

            if(email.text.toString().isEmpty()){
                errorTv.text = "El correo esta vacio"
                errorTv.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if(password.text.toString().isEmpty()){
                errorTv.text = "La contraseña no puede estar vacia"
                errorTv.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if(confirmPassword.text.toString().isEmpty()){
                errorTv.text = "Por favor confirme su contraseña"
                errorTv.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if(!confirmPassword.text.toString().equals(password.text.toString())){
                errorTv.text = "Las contraseñas no coinciden :c"
                errorTv.visibility = View.VISIBLE
                return@setOnClickListener
            }

            errorTv.visibility = View.INVISIBLE
            signIn(email.text.toString().trim(), password.text.toString().trim())

        }


    }



    fun signIn(email: String, password: String){

        Log.d("INFO", "email: ${email}, password: ${password}")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                  //Sign in success, update UI with the signed-in user's information
                    Log.d("INFO", "signInWithEmail:success")

                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }

                else{
                    //if sign in fails, display a message to the user.
                    Log.w("ERROR", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "El registro fallo", Toast.LENGTH_SHORT).show()

                }

            }



    }




}