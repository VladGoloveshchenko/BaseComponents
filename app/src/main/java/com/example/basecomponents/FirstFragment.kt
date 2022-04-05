package com.example.basecomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.basecomponents.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            parentFragmentManager.setFragmentResultListener(
                "result",
                viewLifecycleOwner
            ) { _, bundle ->
                textResult.text = bundle.getBoolean("key").toString()
            }

            button.setOnClickListener {
                val isChecked =
                    textResult.text
                        .takeIf { it.isNotEmpty() }
                        ?.toString()
                        ?.toBoolean() ?: false

                findNavController().navigate(
                    FirstFragmentDirections.toSecond(isChecked)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}