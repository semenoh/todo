package com.semenoh.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.observe
import com.semenoh.todo.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModels<ListViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        todoRecycler.adapter = groupAdapter
        todoRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.items.observe(viewLifecycleOwner) { list ->
            groupAdapter.updateAsync(list)
            refreshLayout.isRefreshing = false
            todoRecycler.smoothScrollToPosition(0)
        }

        addButton.setOnClickListener {
            viewModel.addItem(
                editText.text.toString()
            )
            editText.text = null
        }

        refreshLayout.setOnRefreshListener {
            viewModel.fetch()
        }
    }

}
