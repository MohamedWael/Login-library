package com.github.mohamedwael.login.base

import androidx.lifecycle.Observer
import com.blogspot.mowael.baselibrary.fragments.BaseFragment


open class BaseLoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = BaseLoginFragment()
    }

//    private lateinit var viewModel: BaseLoginViewModel

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//       return super.onCreateView(inflater, container, savedInstanceState)
////        return inflater.inflate(R.layout.base_login_fragment, container, false)
//    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(BaseLoginViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
    fun observeHideKeyboardEvent(viewModel: BaseLoginViewModel) {
        viewModel.hideKeyboardLiveData.observe(viewLifecycleOwner, Observer {
            val hideKeyboard = it.getContentIfNotHandled()
            if (hideKeyboard == true) {
                activity?.also { activity ->
                    hideKeyboard(activity)
                }
            }
        })
    }
}
