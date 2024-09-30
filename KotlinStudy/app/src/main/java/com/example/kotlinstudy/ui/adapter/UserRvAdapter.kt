package com.example.kotlinstudy.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ItemRvUserBinding
import com.example.kotlinstudy.utils.db.entity.UserInfo

class UserRvAdapter(val context: Context, var userList: ArrayList<UserInfo>) : RecyclerView.Adapter<UserRvAdapter.MyViewHolder>() {
    private val TAG : String = "UserRvAdapter"

    class MyViewHolder(var binding: ItemRvUserBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBindData(user : UserInfo){
            binding.tvUserName.setText(user.userName)
            binding.tvUserSex.setText(user.sex)
            binding.tvUserBirthday.setText(user.birthday.toString())
            binding.tvUserWeight.setText(user.weight.toString())
            binding.tvUserHeight.setText(user.height.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding : ItemRvUserBinding = ItemRvUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList.get(position)
        holder.onBindData(user)
    }

    fun updateData(userList : List<UserInfo>){
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
        Log.i(TAG, "updateData: notifyDataSetChanged成功")
    }
}