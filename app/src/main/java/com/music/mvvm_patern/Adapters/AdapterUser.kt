package com.music.mvvm_patern.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.mvvm_patern.data.model.Users
import com.music.mvvm_patern.databinding.UserItemBinding
import com.squareup.picasso.Picasso

class AdapterUser(private var listUser:ArrayList<Users>):RecyclerView.Adapter<AdapterUser.ViewHolder>() {


    fun addUserOne(users: Users){
        listUser.add(users)
        notifyDataSetChanged()
    }
    fun addUservList( list :ArrayList< Users>){
        listUser.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(var itemBinding: UserItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun addUser(users: Users){
           itemBinding.userName.text = users.login
           itemBinding.id.text = "id: ${users.id.toString()}"

           Picasso.get().load(users.avatar_url).into(itemBinding.avatar)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addUser(listUser[position])
    }

    override fun getItemCount(): Int {
       return listUser.size
    }

}