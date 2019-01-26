package com.shohiebsense.myowntracking.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.model.Cat
import com.shohiebsense.myowntracking.ui.adapters.CatsAdapter
import com.shohiebsense.myowntracking.ui.viewmodel.CatViewModel
import com.shohiebsense.myowntracking.utils.Injection
import com.shohiebsense.myowntracking.utils.exts.visible
import kotlinx.android.synthetic.main.fragment_cat.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CatFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CatFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: CatViewModel
    private var adapter = CatsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* arguments?.let {

        }*/



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(context!!))
            .get(CatViewModel::class.java)

        // add dividers between RecyclerView's row items

        initAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        viewModel.searchRepo(query)
    }


    private fun initAdapter() {

        val layoutManager = LinearLayoutManager(context)
        recycler_cat.layoutManager = layoutManager
        recycler_cat.adapter = adapter
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recycler_cat.addItemDecoration(decoration)
        viewModel.repos.observe(this, Observer<PagedList<Cat>> {
            Log.e("shohiebsensee ","observed")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            context.let {
                Toast.makeText(it, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showEmptyList(isEmpty: Boolean) {
        if (isEmpty) {
            text_empty.visibility = View.VISIBLE
            recycler_cat.visibility = View.GONE
        } else {
            text_empty.visibility = View.GONE
            recycler_cat.visibility = View.VISIBLE
        }
    }

    private fun getViewModel(): CatViewModel {
        return ViewModelProviders.of(this).get(CatViewModel::class.java)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {


        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Android"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CatFragment().apply {
                //arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
               // }
            }
    }


}
