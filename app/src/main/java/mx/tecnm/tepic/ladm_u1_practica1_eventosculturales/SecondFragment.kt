package mx.tecnm.tepic.ladm_u1_practica1_eventosculturales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import mx.tecnm.tepic.ladm_u1_practica1_eventosculturales.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.confirmar.setOnClickListener{
            try {
                comprar()
            }catch (e:Exception){

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun comprar(){
        val price = 160
        val total = price * binding.cantidad.text.toString().toInt()
        val tvip = (price*2)*binding.cantidad.text.toString().toInt()
        if (binding.vip.isChecked)binding.total.setText("$${tvip}")
        else binding.total.setText("$${total}")
    }
}