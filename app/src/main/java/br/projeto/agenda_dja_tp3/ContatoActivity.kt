package br.projeto.agenda_dja_tp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.projeto.agenda_dja_tp3.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_contato.*
import java.security.Provider

class ContatoActivity : AppCompatActivity() {

    private lateinit var mDb: FirebaseFirestore

    private var users= mutableListOf<User>()
    private var adapter = UserAdapter(users, this::onUserItemClick)

    private fun onUserItemClick(user: User){
        Toast.makeText(baseContext, "", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

        mDb = FirebaseFirestore.getInstance()
        mDb.collection("users")
            .get()
            .addOnSuccessListener {
                users.addAll(it.toObjects(User::class.java))
                my_recycler_view.adapter=adapter
                my_recycler_view.layoutManager= LinearLayoutManager(this)
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Erro", Toast.LENGTH_LONG).show()
            }
    }
}
