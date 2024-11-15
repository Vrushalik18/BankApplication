package com.cdb22dx005.loginwithretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cdb22dx005.loginwithretrofit.Dashboard.Account

class AccountAdapter(private val context: Context, private val AccountRecyclerView: List<Account>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
        {

        }
    }

    fun setOnItemClickListener(listener : onItemClickListener){

        mListener = listener

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val mediaItemLayoutView = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.list_view, viewGroup, false
        )
        return  MediaViewHolder(mediaItemLayoutView,mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var accountViewHolder = holder as MediaViewHolder
        var accountItem = AccountRecyclerView[position] as Account

        accountViewHolder.Title.text =  accountItem.displayName
        accountViewHolder.Description.text = "account Balance is $" + accountItem.accountBalance

    }

    override fun getItemCount(): Int {
        return AccountRecyclerView.size
    }
}

class MediaViewHolder(view: View,listener: AccountAdapter.onItemClickListener) : RecyclerView.ViewHolder(view) {

    val Title: TextView
    val Description: TextView

    init {

        Title = view.findViewById(R.id.tvHeading)
        Description = view.findViewById(R.id.subHeading)
    }
    init{
        view.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

}
