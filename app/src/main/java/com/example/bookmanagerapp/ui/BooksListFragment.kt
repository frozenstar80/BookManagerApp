package com.example.bookmanagerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmanagerapp.adapter.BooksAdapter
import com.example.bookmanagerapp.model.BooksInfo
import com.example.bookmanagerapp.viewmodel.BooksViewModel
import com.example.bookmanagerapp.R
import com.example.bookmanagerapp.databinding.FragmentBooksListBinding
import com.example.bookmanagerapp.utils.DeleteBook
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksListFragment : Fragment(R.layout.fragment_books_list) , DeleteBook{

    private var _binding: FragmentBooksListBinding? = null
    private val binding get() = _binding!!
    private lateinit var booksAdapter: BooksAdapter
    private val viewModel: BooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBooksListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.fabAddTask.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_booksListFragment_to_booksFragment
            )
        }
    }

    private fun setupRecyclerView() {

        booksAdapter = BooksAdapter(this@BooksListFragment,requireActivity())

        binding.rvTodoList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = booksAdapter
        }

        viewModel.allBooks.observe(requireActivity()) { listTodo ->
            updateUi(listTodo)
            booksAdapter.bookList = listTodo
        }
    }

    private fun updateUi(list: List<BooksInfo>) {
        if (list.isNotEmpty()) {
            binding.rvTodoList.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE
        } else {
            binding.rvTodoList.visibility = View.GONE
            binding.cardView.visibility = View.VISIBLE
        }
    }

    override fun onDelete(booksInfo: BooksInfo) {
        viewModel.deleteBook(booksInfo)
        Toast.makeText(requireActivity(),"Info Deleted",Toast.LENGTH_SHORT).show()
    }


}