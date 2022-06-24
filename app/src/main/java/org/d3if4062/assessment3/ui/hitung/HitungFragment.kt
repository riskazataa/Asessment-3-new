package org.d3if4062.assessment3.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if4062.assessment3.R
import org.d3if4062.assessment3.databinding.FragmentHitungBinding
import org.d3if4062.assessment3.db.PajakDb
import org.d3if4062.assessment3.model.HasilPajak


class HitungFragment : Fragment() {

    private val viewModel: HitungViewModel by lazy {
        val db = PajakDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_objek_non_ppn -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_objekNonPpnFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private lateinit var binding: FragmentHitungBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitung() }
        viewModel.getHasilPajak().observe(requireActivity(), { showResult(it) })
        binding.shareButton.setOnClickListener { shareData() }
    }

    private fun hitung() {
        val nama = binding.etNama.text.toString()
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val harga = binding.etHarga.text.toString()
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(context, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val jumlah = binding.etJumlah.text.toString()
        if (TextUtils.isEmpty(jumlah.toString())) {
            Toast.makeText(context, R.string.jumlah_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitung(
            nama.toString(),
            harga.toDouble(),
            jumlah.toDouble()
        )


    }
    private fun showResult(result: HasilPajak?) {
        if (result == null) return
        binding.tvHasil.text = getString(R.string.hasil_x, result.ppn)
        binding.tvTotal.text = getString(R.string.total_x,result.total)
    }
    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.tvHasil.text,
            binding.tvTotal.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }


}




