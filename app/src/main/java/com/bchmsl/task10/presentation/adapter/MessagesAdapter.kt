package com.bchmsl.task10.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.task10.R
import com.bchmsl.task10.common.extension.loadImage
import com.bchmsl.task10.common.extension.toTime
import com.bchmsl.task10.common.model.MessageResponseDto
import com.bchmsl.task10.databinding.LayoutMessageItemBinding

class MessagesAdapter : ListAdapter<MessageResponseDto, MessagesAdapter.MessagesViewHolder>(callback) {
    inner class MessagesViewHolder(private val binding: LayoutMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                currentItem.avatar?.let { civAvatar.loadImage(it) }
                tvFullName.text = currentItem.firstName.plus(" "+currentItem.lastName)
                currentItem.updatedDate?.let { tvTime.text = it.toTime() }

                when (currentItem.unreaMessage) {
                    0 -> {
                        tvBadge.visibility = View.GONE
                        tvLastMessage.setTextColor(Color.GRAY)
                        tvTime.setTextColor(Color.GRAY)
                    }
                    1 -> {
                        tvBadge.visibility = View.GONE
                        tvLastMessage.setTextColor(Color.WHITE)
                        tvTime.setTextColor(Color.WHITE)
                    }
                    else -> {
                        tvBadge.visibility = View.VISIBLE
                        tvBadge.text = currentItem.unreaMessage.toString()
                        tvLastMessage.setTextColor(Color.WHITE)
                        tvTime.setTextColor(Color.WHITE)
                    }
                }
                when (currentItem.isTyping) {
                    true -> ivTyping.visibility = View.VISIBLE
                    else -> ivTyping.visibility = View.GONE
                }
                when (currentItem.messageType) {
                    "voice" -> {
                        tvLastMessage.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.ic_recorder,
                            0,
                            0,
                            0
                        )
                        tvLastMessage.text = "Sent a voice message"
                    }
                    "attachment" -> {
                        tvLastMessage.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.ic_attachment,
                            0,
                            0,
                            0
                        )
                        tvLastMessage.text = "Sent an attachment"
                    }
                    else -> {tvLastMessage.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        0,
                        0
                    )
                        tvLastMessage.text = currentItem.lastMessage
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        return MessagesViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.bind()
    }

    companion object{
        val callback = object:DiffUtil.ItemCallback<MessageResponseDto>(){
            override fun areItemsTheSame(
                oldItem: MessageResponseDto,
                newItem: MessageResponseDto
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MessageResponseDto,
                newItem: MessageResponseDto
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}
