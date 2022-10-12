package com.example.classwork10.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.classwork10.R
import com.example.classwork10.databinding.SinglechatItemBinding
import com.example.classwork10.domain.model.ChattingModel
import com.gabo.quiz10.comon.extensions.loadImage
import com.gabo.quiz10.comon.helpers.MessageType

class ChattingAdapter() : RecyclerView.Adapter<ChattingAdapter.ChatVH>() {
    private var list: List<ChattingModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ChattingModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ChatVH(private val binding: SinglechatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ChattingModel) {
            with(binding) {
                ivIcon.loadImage(model.avatar)
                tvName.text = "${model.firstName} ${model.lastName}"
                if (model.isTyping) {
                    cvUnreadMessages.visibility = View.GONE
                    ivTyping.visibility = View.VISIBLE
                }
                if (model.unreadMessage != 0) {
                    tvMessage.setTypeface(null, Typeface.BOLD)
                    tvMessage.setTextColor(
                        ContextCompat.getColor(tvMessage.context, R.color.white)
                    )
                    if (model.unreadMessage > 1){
                        if (model.isTyping) {
                            cvUnreadMessages.visibility = View.GONE
                            ivTyping.visibility = View.VISIBLE
                        } else {
                            cvUnreadMessages.visibility = View.VISIBLE
                            tvUnreadMessages.text = model.unreadMessage.toString()
                            ivTyping.visibility = View.GONE
                        }
                    }
                } else {
                    tvMessage.setTypeface(null, Typeface.NORMAL)
                    tvMessage.setTextColor(
                        ContextCompat.getColor(tvMessage.context, R.color.txt_gray)
                    )
                }
                when (model.messageType) {
                    MessageType.text.name -> {
                        tvMessage.text = model.lastMessage
                        cvVoiceOrAttachment.visibility = View.GONE
                    }
                    MessageType.attachment.name -> {
                        cvVoiceOrAttachment.visibility = View.VISIBLE
                        ivVoiceOrAttachment.setImageResource(R.drawable.ic_attachment)
                        tvMessage.text = " Sent an attachment"
                    }
                    MessageType.voice.name -> {
                        cvVoiceOrAttachment.visibility = View.VISIBLE
                        ivVoiceOrAttachment.setImageResource(R.drawable.ic_voice)
                        tvMessage.text = " Sent a voice message"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatVH {
        val binding =
            SinglechatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatVH(binding)
    }

    override fun onBindViewHolder(holder: ChatVH, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
}