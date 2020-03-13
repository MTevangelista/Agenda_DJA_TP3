package br.projeto.agenda_dja_tp3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.projeto.agenda_dja_tp3.model.User
import kotlinx.android.synthetic.main.layout_user.view.*

class UserAdapter(private val users: List<User>, private val callback: (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val  v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_user, parent, false)
        val viewHolder = UserViewHolder(v)

        viewHolder.itemView.setOnClickListener{
            val user = users[viewHolder.adapterPosition]
            callback(user)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val (nome, email, senha, celular, telefone, CPF, cidade) = users[position]
        holder.txtVwNome.text = nome
        holder.txtVwCelular.text = celular
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtVwNome: TextView = itemView.textView_Nome
        val txtVwCelular: TextView = itemView.textView_Celular
    }
}