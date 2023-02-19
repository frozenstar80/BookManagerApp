package com.example.bookmanagerapp.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bookmanagerapp.model.BooksInfo
import com.example.bookmanagerapp.viewmodel.BooksViewModel
import com.example.bookmanagerapp.R
import com.example.bookmanagerapp.databinding.FragmentAddBookBinding
import com.example.bookmanagerapp.utils.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBooksFragment : Fragment(R.layout.fragment_add_book) {

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!
    private var bookName = arrayOf<String?>(
        "Book A",
        "Book B",
        "Book C"
    )
    private val viewModel: BooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(Constants.NAME)
        val number = arguments?.getString(Constants.NUMBER)
        val selectedBookName = arguments?.getString(Constants.BOOKNAME)
        val id = arguments?.getInt(Constants.ID)


        val arrayAdapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(requireContext(), R.layout.my_selected_item, bookName)
        arrayAdapter.setDropDownViewResource(R.layout.my_selected_item)
        binding.selectBooks.adapter = arrayAdapter

        binding.btnAddTask.setOnClickListener { mView ->
            if (id == null)
                saveNote(mView)
            else
                updateNote(mView,name.toString(),number.toString(),selectedBookName.toString(),id)
        }


        if (!name.isNullOrEmpty()){
            binding.etName.setText(name)
            binding.etNumber.setText(number)
            for (i in bookName.indices)
            {
                if(bookName[i]==selectedBookName)
                {
                    binding.selectBooks.setSelection(i,true)
                }
            }
        }


        binding.btnCancel.setOnClickListener {
            binding.etName.text.clear()
            binding.etNumber.text.clear()
            view.findNavController().navigate(
                R.id.action_booksFragment_to_booksListFragment
            )
        }
    }

    private fun updateNote(mView: View, name: String, number: String, selectedBookName: String, id: Int) {
        val bookInfo = BooksInfo(id, name,number,selectedBookName)
        viewModel.updateBook(bookInfo)
        mView.findNavController().navigate(
            R.id.action_booksFragment_to_booksListFragment
        )
        Snackbar.make(mView, "Info Updated Successfully",
            Snackbar.LENGTH_SHORT).show()
    }


    private fun saveNote(view: View) {
        val userName = binding.etName.text.toString()
        val userNumber = binding.etNumber.text.toString()
        val bookName = binding.selectBooks.selectedItem.toString()

        if (userName.isNotEmpty() && userNumber.isNotEmpty()) {
            val bookInfo = BooksInfo(0, userName,userNumber,bookName)

            viewModel.insertBook(bookInfo)

            Snackbar.make(view, "Info Saved Successfully",
                Snackbar.LENGTH_SHORT).show()

            view.findNavController().navigate(
                R.id.action_booksFragment_to_booksListFragment
            )

        } else {
            val toast = Toast.makeText(activity,
                "User Name and Number can not be empty",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}