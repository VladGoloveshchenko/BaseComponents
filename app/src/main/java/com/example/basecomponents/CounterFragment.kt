package com.example.basecomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.basecomponents.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private var _binding: FragmentCounterBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val args by navArgs<CounterFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCounterBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            var counter = args.keyCounter

            textCounter.text = counter.toString()

            textCounter.setOnClickListener {
                Toast.makeText(root.context, "some text", Toast.LENGTH_SHORT)
                    .show()
            }

            buttonPlus.setOnClickListener {
                textCounter.text = (++counter).toString()
            }

            buttonMinus.setOnClickListener {
                textCounter.text = (--counter).toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}