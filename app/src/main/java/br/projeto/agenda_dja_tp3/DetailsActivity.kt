package br.projeto.agenda_dja_tp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.projeto.agenda_dja_tp3.model.User
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var user = intent.getSerializableExtra("user") as User
        textViewNome.text = user.nome
        textViewEmail.text = user.email
        textViewSenha.text = user.senha
        textViewCelular.text = user.celular
        textViewTelefone.text = user.telefone
        textViewCPF.text= user.CPF
    }
}
