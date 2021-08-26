package gr.repo.kincart.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gr.imdb.movies.viewmodels.CaseStudiesViewModel
import gr.repo.kincart.R
import gr.repo.kincart.adapters.CaseStudyAdapter
import gr.repo.kincart.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var caseStudiesViewModel: CaseStudiesViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CaseStudyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        caseStudiesViewModel = ViewModelProvider(requireActivity()).get(CaseStudiesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setUpObservers()

    }

    private fun initViews() {
        showShimmerEffect()
        caseStudiesViewModel.getCaseStudies()

        // setup adapter
        adapter = CaseStudyAdapter(requireContext(), mutableListOf())
        binding.caseStudyRV.layoutManager = LinearLayoutManager(requireContext())
        binding.caseStudyRV.adapter = adapter
    }

    private fun setUpObservers() {
        caseStudiesViewModel.caseStudy.observe(viewLifecycleOwner, Observer { studiesList ->
            adapter.updateItems(studiesList.caseStudies)
            hideShimmerEffect()
        })
    }

    private fun showShimmerEffect() {
        binding.caseStudyRV.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.caseStudyRV.hideShimmer()
    }
}