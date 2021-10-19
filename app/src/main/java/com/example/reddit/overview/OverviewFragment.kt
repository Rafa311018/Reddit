package com.example.reddit.overview

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reddit.R
import com.example.reddit.databinding.FragmentOverviewBinding
import android.net.Uri

import android.content.Intent




class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.listItems.adapter = ItemAdapter(OnClickListener { string ->
            if (string.startsWith("http://") || string.startsWith("https://"))
            {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(string))
                startActivity(browserIntent)
            }else {
                if (string != "") {
                    showDialog(string)
                } else {
                    Toast.makeText(context, "There is no self Text", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.showSelftText.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                showDialog(it.RedditDataProperty.selftext)
            }
        })

        return binding.root
    }

    fun showDialog(selfText: String?) {
        val alerDialog: AlertDialog = activity.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("$selfText")
            builder.apply {
                setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            }
            builder.create()
        }
        alerDialog.show()
    }
}