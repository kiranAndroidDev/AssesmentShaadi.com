package com.example.demoshaadi.main

import android.content.Context
import android.databinding.BindingAdapter
import android.support.annotation.AnimRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.demoshaadi.api.model.Results
import com.example.demoshaadi.R
import com.example.demoshaadi.databinding.UserListItemBinding
import com.squareup.picasso.Picasso


class UserListAdapter(private val context: Context, private val resultsList: MutableList<Results>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    fun addNewsList(newsList: List<Results>) {
        if (!this.resultsList.isEmpty()) {
            this.resultsList.clear()
        }
        this.resultsList.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(UserListItemBinding.inflate(layoutInflater, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val resultsEntity = resultsList[i]
        viewHolder.bind(resultsEntity)
        viewHolder.binding.description = resultsEntity.dob.age.toString() + ", " + context.getString(R.string.space) +
                resultsEntity.location.street +
                context.getString(R.string.space) + resultsEntity.location.city +
                context.getString(R.string.space) + resultsEntity.location.state +
                context.getString(R.string.space) + resultsEntity.location.timezone.description +
                context.getString(R.string.space) + resultsEntity.location.postcode
        viewHolder.binding.acceptImg.setOnClickListener { v ->
            viewHolder.binding.isConnect = true
            slideView(viewHolder.binding.cVItem, i, R.anim.slide_out_right)
        }
        viewHolder.binding.declineImg.setOnClickListener { v ->
            viewHolder.binding.isDecline = true
            slideView(viewHolder.binding.cVItem, i, R.anim.slide_out_left)
        }
        viewHolder.binding.isConnect = false
        viewHolder.binding.isDecline = false
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

    inner class ViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.result = results
            binding.executePendingBindings()
        }
    }

    private fun slideView(view: View, i: Int, @AnimRes anim: Int) {
        val animation = AnimationUtils.loadAnimation(context, anim)
        //use this to make it longer:  animation.setDuration(1000);
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                resultsList.removeAt(i)
                notifyDataSetChanged()
            }
        })
        view.startAnimation(animation)
    }

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun ImageView.setImageUrl(imageUrl: String) {
            Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    }
}

