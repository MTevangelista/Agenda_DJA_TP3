package br.projeto.agenda_dja_tp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import br.projeto.agenda_dja_tp3.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mDb: FirebaseFirestore

    private lateinit var userNome: Editable
    private lateinit var userEmail: Editable
    private lateinit var userSenha: Editable
    private lateinit var userCelular: Editable
    private lateinit var userTelefone: Editable
    private lateinit var userCPF: Editable
    private lateinit var userCidade: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNome = editText_Nome.text
        userEmail = editText_Email.text
        userSenha = editText_Senha.text
        userCelular = editText_Celular.text
        userTelefone = editText_Telefone.text
        userCPF = editText_CPF.text
        userCidade = editText_Cidade.text

        mDb = FirebaseFirestore.getInstance()
        setListener()
    }

    override fun onClick(view: View) {
        var btnId = view.id
//        userNome = editText_Nome.text
//        userEmail = editText_Email.text
//        userSenha = editText_Senha.text
//        userCelular = editText_Celular.text
//        userTelefone = editText_Telefone.text
//        userCPF = editText_CPF.text
//        userCidade = editText_Cidade.text

        when (btnId) {
            R.id.button_Salvar -> {
                if (userNome.toString().isNullOrEmpty() or userEmail.toString().isNullOrEmpty() or userSenha.toString().isNullOrEmpty() or
                    userCelular.toString().isNullOrEmpty() or userTelefone.toString().isNullOrEmpty() or userCPF.toString().isNullOrEmpty() or userCidade.toString().isNullOrEmpty()) {
                    showToast("Por favor, preencha todos os campos!")
                } else {
                    createUser(userNome.toString(), userEmail.toString(),userSenha.toString(), userCelular.toString(),
                        userTelefone.toString(), userCPF.toString() ,userCidade.toString())
                    userNome.clear()
                    userEmail.clear()
                    userSenha.clear()
                    userCelular.clear()
                    userTelefone.clear()
                    userCPF.clear()
                    userCidade.clear()
                    showToast("Usuário cadastrado com sucesso!")
                }
            }
            R.id.button_Limpar -> {
                userNome.clear()
                userEmail.clear()
                userSenha.clear()
                userCelular.clear()
                userTelefone.clear()
                userCPF.clear()
                userCidade.clear()
            }
            R.id.button_VisualizarContatos -> {
                startActivity(Intent(baseContext, ContatoActivity::class.java))
            }
        }
    }

    private fun setListener(){
        button_Salvar.setOnClickListener(this)
        button_Limpar.setOnClickListener(this)
        button_VisualizarContatos.setOnClickListener(this)
    }

    private fun createUser(userNome: String, userEmail: String, userSenha: String, userCelular: String, userTelefone: String, userCPF: String, userCidade: String){
        mDb = FirebaseFirestore.getInstance()
        mDb.collection("users")
            .document(userEmail)
            .set(User(userNome, userEmail, userSenha, userCelular, userTelefone, userCPF, userCidade))
            .addOnFailureListener{
                showToast("Erro no cadastro do usuário!")
            }
    }

    private fun showToast(string: String){
        Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }
}
