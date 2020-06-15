package com.semenoh.todo.view

import android.graphics.Paint
import com.semenoh.todo.R
import com.semenoh.todo.entity.Task
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_todo.view.*

class ListItemTodo(
    private val entry: Task,
    private val onChecked: (Task) -> Unit
): Item() {
    override fun getLayout(): Int = R.layout.item_todo

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with (viewHolder.itemView) {
            checkbox.text = entry.title
            checkbox.setOnCheckedChangeListener(null)
            checkbox.isChecked = entry.completed
            checkbox.setOnCheckedChangeListener { _, _ ->
                onChecked(entry)
            }

            isSelected = entry.completed
        }
    }

    override fun getId(): Long {
        return entry.id
    }

    override fun hasSameContentAs(other: com.xwray.groupie.Item<*>): Boolean {
        return if (other is ListItemTodo) {
            entry == other.entry
        } else {
            false
        }
    }
}