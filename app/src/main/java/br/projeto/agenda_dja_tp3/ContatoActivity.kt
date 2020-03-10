package br.projeto.agenda_dja_tp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import br.projeto.agenda_dja_tp3.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_contato.*

class ContatoActivity : AppCompatActivity() {

    private lateinit var mDb: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)
        var listUsers = mutableListOf<User>()

        mDb = FirebaseFirestore.getInstance()
        mDb.collection("users")
            .get()
            .addOnSuccessListener {
                listUsers.addAll(it.toObjects(User::class.java))
                listView_Contatos.adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, listUsers)
                listView_Contatos.setOnItemClickListener{
                    parent, view, position, id ->
                    var user = listUsers.get(position) as User
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Erro", Toast.LENGTH_LONG).show()
            }
    }
}
