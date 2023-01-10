package com.example.viewpagerproblem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.viewpagerproblem.ui.data.ICyclicViewPager

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var viewPager: ICyclicViewPager
    private lateinit var changeButton: Button
    private lateinit var currentLocaleTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        viewPager = view.findViewById(R.id.cyclic_view_pager_widget)
        changeButton = view.findViewById(R.id.change_button)
        currentLocaleTv = view.findViewById(R.id.current_locale_tv)

        viewPager.initialize(
            onClickedCallback = { id ->
                Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
            },
            onCloseClickedCallback = { id ->
                Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
            }
        )
        changeButton.setOnClickListener {
            viewModel.changeLocale()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.state.observe(viewLifecycleOwner) {
            viewPager.setState(it)
            currentLocaleTv.text = "Current Locale: ${it.curentLocale.name}"
        }
    }
}